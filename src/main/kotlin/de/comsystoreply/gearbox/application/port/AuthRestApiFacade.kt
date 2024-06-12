package de.comsystoreply.gearbox.application.port

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationSignUpCommand
import org.springframework.http.ResponseEntity

interface AuthRestApiFacade {
    fun signIn(authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto>
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