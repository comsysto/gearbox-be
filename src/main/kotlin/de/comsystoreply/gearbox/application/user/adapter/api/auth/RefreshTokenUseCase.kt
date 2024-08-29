package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.security.config.JwtProperties
import de.comsystoreply.gearbox.application.security.repository.RefreshTokenRepository
import de.comsystoreply.gearbox.application.security.service.TokenService
import de.comsystoreply.gearbox.application.user.port.web.InvalidOrExpiredTokenException
import de.comsystoreply.gearbox.application.user.port.web.RefreshTokenRequestDto
import de.comsystoreply.gearbox.application.user.port.web.RefreshTokenResponseDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class RefreshTokenUseCase(
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun execute(request: RefreshTokenRequestDto): RefreshTokenResponseDto {
        val extractedEmail = tokenService.extractEmail(request.refreshToken)
        val refreshTokenUser = refreshTokenRepository.findUserDetailsByToken(request.refreshToken)

        if (tokenService.isExpired(request.refreshToken) && refreshTokenUser?.email != extractedEmail) {
            throw InvalidOrExpiredTokenException("Token is invalid or expired.")
        }

        val accessToken = createAccessToken(extractedEmail!!)
        val refreshToken = createRefreshToken(extractedEmail)
        return RefreshTokenResponseDto(accessToken, refreshToken)
    }

    private fun createAccessToken(email: String): String =
        tokenService.generate(email, expirationDate = getAccessTokenExpirationDate())

    private fun createRefreshToken(email: String): String =
        tokenService.generate(email, expirationDate = getRefreshTokenExpirationDate())

    private fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    private fun getRefreshTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
}