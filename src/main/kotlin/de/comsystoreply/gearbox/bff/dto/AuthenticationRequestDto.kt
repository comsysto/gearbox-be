package de.comsystoreply.gearbox.bff.dto

import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationSignUpCommand

data class AuthenticationRequestDto(
    val email: String,
    val username: String?,
    val password: String,
    val confirmPassword: String?
) {
    fun toDomain(profileImageUrl: String?) = AuthenticationSignUpCommand(
        email,
        username!!,
        password,
        confirmPassword,
        profileImageUrl
    )
}