package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.security.config.JwtProperties
import de.comsystoreply.gearbox.application.security.repository.RefreshTokenRepository
import de.comsystoreply.gearbox.application.security.service.TokenService
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationResponseDto
import org.springframework.stereotype.Component
import java.util.*

@Component
class GenerateTokenUseCase(
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun execute(user: UserEntity): AuthenticationResponseDto {
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

    private fun createAccessToken(email: String): String =
        tokenService.generate(email, expirationDate = getAccessTokenExpirationDate())

    private fun createRefreshToken(email: String): String =
        tokenService.generate(email, expirationDate = getRefreshTokenExpirationDate())

    private fun getAccessTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)

    private fun getRefreshTokenExpirationDate(): Date =
        Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
}