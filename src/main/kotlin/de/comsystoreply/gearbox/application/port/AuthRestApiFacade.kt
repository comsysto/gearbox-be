package de.comsystoreply.gearbox.application.port

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationSignUpCommand
import org.springframework.http.ResponseEntity

/**
 * Interface which defines methods which expose Authentication features via REST API
 */
interface AuthRestApiFacade {
    /**
     * Function gets sign in request from the user with credentials and returns token with user data if it exists
     * @property [authenticationRequestDto] contains user credentials
     * @return token and user if credentials are valid, else returns error message
     */
    fun signIn(authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto>
    /**
     * Function gets sign up request from the user with credentials and returns token with created user data
     * @property [authenticationRequestDto] contains user credentials
     * @return token and created user if credentials are valid and user doesn't exist yet, else returns error message
     */
    fun signUp(authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto>
}

data class AuthenticationRequestDto(
    val email: String,
    val username: String? = null,
    val password: String,
    val confirmPassword: String? = null
) {
    fun toDomain(profileImageUrl: String?) = AuthenticationSignUpCommand(
        email,
        username!!,
        password,
        confirmPassword,
        profileImageUrl
    )
}

data class AuthenticationResponseDto(
    val token: String,
    val user: User,
)