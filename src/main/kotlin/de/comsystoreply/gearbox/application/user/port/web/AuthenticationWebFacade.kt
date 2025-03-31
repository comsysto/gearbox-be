package de.comsystoreply.gearbox.application.user.port.web

import de.comsystoreply.gearbox.domain.user.port.api.UserInputDetails

/**
 * Interface which defines methods that expose Authentication features via REST API
 */
interface AuthenticationWebFacade {
    /**
     * Function gets sign in request from the user with credentials and returns token with user data if it exists
     * @property [request] contains user credentials
     * @return token and user if credentials are valid
     * @throws AuthenticationException on invalid sign in attempt
     */
    fun signIn(request: AuthenticationRequestDto): AuthenticationResponseDto

    /**
     * Function gets sign up request from the user with credentials and returns token with created user data
     * @property [request] contains user credentials
     * @return token and created user if credentials are valid and user doesn't exist yet
     * @throws AuthenticationException on invalid sign up attempt
     */
    fun signUp(request: AuthenticationRequestDto): AuthenticationResponseDto

    /**
     * Function gets refresh token request and returns new access token
     * @property [request] contains old access token
     * @return user with new access token and refresh token
     * @throws InvalidOrExpiredTokenException on invalid refresh token attempt
     */
    fun refreshToken(request: RefreshTokenRequestDto): AuthenticationResponseDto
}

data class AuthenticationRequestDto(
    val email: String,
    val username: String? = null,
    val password: String,
    val confirmPassword: String? = null
) {
    fun toDomain(profileImageUrl: String? = null) = UserInputDetails(
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

sealed class AuthenticationException(message: String) : Exception(message)

class InvalidOrExpiredTokenException(message: String) : AuthenticationException(message)