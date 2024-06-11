package de.comsystoreply.gearbox.bff.dto

import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationSignUpCommand

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