package de.comsystoreply.gearbox.application.user.port.web

import de.comsystoreply.gearbox.domain.user.port.api.UserDetails

/**
 * Interface which defines methods which expose Authentication features via REST API
 */
interface AuthenticationWebFacade {
    /**
     * Function gets sign in request from the user with credentials and returns token with user data if it exists
     * @property [request] contains user credentials
     * @return token and user if credentials are valid, else returns error message
     */
    fun signIn(request: AuthenticationRequestDto): AuthenticationResponseDto

    /**
     * Function gets sign up request from the user with credentials and returns token with created user data
     * @property [request] contains user credentials
     * @return token and created user if credentials are valid and user doesn't exist yet, else returns error message
     */
    fun signUp(request: AuthenticationRequestDto): AuthenticationResponseDto

    /**
     * Function gets refresh token request and returns new access token
     * @property [request] contains old access token
     * @return new access token along with refresh token, else returns error message
     */
    fun refreshToken(request: RefreshTokenRequestDto): RefreshTokenResponseDto
}

data class AuthenticationRequestDto(
    val email: String,
    val username: String? = null,
    val password: String,
    val confirmPassword: String? = null
) {
    fun toDomain(profileImageUrl: String? = null) = UserDetails(
        email,
        username ?: "",
        password,
        confirmPassword,
        profileImageUrl
    )
}

data class AuthenticationResponseDto(
    val token: String,
    val refreshToken: String,
    val id: String,
    val email: String,
    val username: String,
    val profileImageUrl: String?
)

data class RefreshTokenRequestDto(val refreshToken: String)

data class RefreshTokenResponseDto(
    val token: String,
    val refreshToken: String,
)

sealed class AuthenticationException(message: String) : Exception(message)

class InvalidOrExpiredTokenException(message: String) : AuthenticationException(message)