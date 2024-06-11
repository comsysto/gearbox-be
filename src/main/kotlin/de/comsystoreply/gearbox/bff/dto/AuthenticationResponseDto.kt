package de.comsystoreply.gearbox.bff.dto

import de.comsystoreply.gearbox.domain.user.model.User

data class AuthenticationResponseDto(
    val token: String,
    val username: User,
)
