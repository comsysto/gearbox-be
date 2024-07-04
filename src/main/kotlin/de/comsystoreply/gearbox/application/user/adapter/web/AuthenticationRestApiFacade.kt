package de.comsystoreply.gearbox.application.user.adapter.web

import de.comsystoreply.gearbox.application.security.config.JwtProperties
import de.comsystoreply.gearbox.application.security.repository.RefreshTokenRepository
import de.comsystoreply.gearbox.application.security.service.TokenService
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignUpUseCase
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.*
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationRestApiFacade(
    private val userSignInUseCase: UserSignInUseCase,
    private val userSignUpUseCase: UserSignUpUseCase,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
) : AuthenticationWebFacade {

    override fun signIn(request: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignInUseCase.execute(request)
        return createResponse(user)
    }

    override fun signUp(request: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignUpUseCase.execute(request)
        return createResponse(user)
    }

    override fun refreshToken(request: RefreshTokenRequestDto): RefreshTokenResponseDto {
        val extractedEmail = tokenService.extractEmail(request.refreshToken)
        val refreshTokenUser = refreshTokenRepository.findUserDetailsByToken(request.refreshToken)

        if (tokenService.isExpired(request.refreshToken) && refreshTokenUser?.email != extractedEmail) {
            throw InvalidOrExpiredTokenException("Token is invalid or expired.")
        }

        val accessToken = createAccessToken(extractedEmail!!)
        val refreshToken = createRefreshToken(extractedEmail)
        return RefreshTokenResponseDto(accessToken, refreshToken)
    }

    private fun createResponse(user: UserEntity): AuthenticationResponseDto {
        val accessToken = createAccessToken(user.email)
        val refreshToken = createRefreshToken(user.email)

        refreshTokenRepository.save(refreshToken, user)

        return AuthenticationResponseDto(
            accessToken,
            refreshToken,
            user.id,
            user.email,
            user.username,
            user.profileImageUrl
        )
    }

    private fun createAccessToken(email: String) =
        tokenService.generate(email, expirationDate = getAccessTokenExpirationDate())

    private fun createRefreshToken(email: String) =
        tokenService.generate(email, expirationDate = getRefreshTokenExpirationDate())

    private fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    private fun getRefreshTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
}