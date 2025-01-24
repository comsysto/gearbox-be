package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.security.repository.RefreshTokenRepository
import de.comsystoreply.gearbox.application.security.service.TokenService
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.InvalidOrExpiredTokenException
import de.comsystoreply.gearbox.application.user.port.web.RefreshTokenRequestDto
import org.springframework.stereotype.Component

@Component
class GetTokenOwnerUseCase(
    private val tokenService: TokenService,
    private val refreshTokenRepository: RefreshTokenRepository
) {

    fun execute(request: RefreshTokenRequestDto): UserEntity {
        println("Request: $request")
        val extractedEmail = tokenService.extractEmail(request.refreshToken)
        val refreshTokenUser = refreshTokenRepository.findUserDetailsByToken(request.refreshToken)

        println("refresh token user: $refreshTokenUser")
        if (refreshTokenUser == null || isTokenInvalidOrExpired(request.refreshToken, refreshTokenUser.email, extractedEmail)) {
            throw InvalidOrExpiredTokenException("Token is invalid or expired.")
        }

        return refreshTokenUser
    }

    private fun isTokenInvalidOrExpired(refreshToken: String, userEmail: String, extractedEmail: String?): Boolean {
        println("refreshToken: $refreshToken, userEmail: $userEmail, extractedEmail $extractedEmail")
        println("Is expired: ${tokenService.isExpired(refreshToken)}")
        return tokenService.isExpired(refreshToken) && userEmail != extractedEmail
    }
}