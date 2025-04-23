package de.comsystoreply.gearbox.domain.user.model

data class User(
    val id: String,
    val email: String,
    val username: String,
    val password: String,
    var profileImageUrl: String?
)
