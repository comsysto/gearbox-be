package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.security.repository.RefreshTokenRepository
import de.comsystoreply.gearbox.application.security.service.TokenService
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.InvalidOrExpiredTokenException
import org.springframework.stereotype.Component

@Component
class GetTokenOwnerUseCase(
    private val tokenService: TokenService,
    private val refreshTokenRepository: RefreshTokenRepository
) {
    fun execute(token: String): UserEntity {
        val extractedEmail = tokenService.extractEmail(token)
        val refreshTokenUser = refreshTokenRepository.findUserDetailsByToken(token)

        if (refreshTokenUser == null || isTokenInvalidOrExpired(token, refreshTokenUser.email, extractedEmail)) {
            throw InvalidOrExpiredTokenException("Token is invalid or expired.")
        }

        return refreshTokenUser
    }

    private fun isTokenInvalidOrExpired(refreshToken: String, userEmail: String, extractedEmail: String?): Boolean {
        return tokenService.isExpired(refreshToken) && userEmail != extractedEmail
    }
}