package de.comsystoreply.gearbox.application.user.adapter.web

import de.comsystoreply.gearbox.application.security.config.JwtProperties
import de.comsystoreply.gearbox.application.security.service.TokenService
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignUpUseCase
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationResponseDto
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationWebFacade
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationRestApiFacade(
    private val userSignInUseCase: UserSignInUseCase,
    private val userSignUpUseCase: UserSignUpUseCase,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
) : AuthenticationWebFacade {
    override fun signIn(request: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignInUseCase.execute(request)
        val accessToken = createAccessToken(user)

        return AuthenticationResponseDto(accessToken, user.id, user.email, user.username, user.profileImageUrl)
    }

    override fun signUp(request: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignUpUseCase.execute(request)
        val accessToken = createAccessToken(user)

        return AuthenticationResponseDto(accessToken, user.id, user.email, user.username, user.profileImageUrl)
    }

    private fun createAccessToken(user: UserEntity) =
        tokenService.generate(user, expirationDate = getAccessTokenExpirationDate())

    private fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
}