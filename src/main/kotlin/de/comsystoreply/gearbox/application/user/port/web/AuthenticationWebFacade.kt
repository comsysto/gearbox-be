package de.comsystoreply.gearbox.application.user.port.web

import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationDetails

/**
 * Interface which defines methods which expose Authentication features via REST API
 */
interface AuthenticationWebFacade {
    /**
     * Function gets sign in request from the user with credentials and returns token with user data if it exists
     * @property [authenticationRequestDto] contains user credentials
     * @return token and user if credentials are valid, else returns error message
     */
    fun signIn(authenticationRequestDto: AuthenticationRequestDto): AuthenticationResponseDto

    /**
     * Function gets sign up request from the user with credentials and returns token with created user data
     * @property [authenticationRequestDto] contains user credentials
     * @return token and created user if credentials are valid and user doesn't exist yet, else returns error message
     */
    fun signUp(authenticationRequestDto: AuthenticationRequestDto): AuthenticationResponseDto
}

data class AuthenticationRequestDto(
    val email: String,
    val username: String? = null,
    val password: String,
    val confirmPassword: String? = null
) {
    fun toDomain(profileImageUrl: String? = null) = AuthenticationDetails(
        email,
        username ?: "",
        password,
        confirmPassword,
        profileImageUrl
    )
}

data class AuthenticationResponseDto(
    val token: String,
    val id: String,
    val email: String,
    val username: String,
    val profileImageUrl: String?
)