package de.comsystoreply.gearbox.application.user.model

import de.comsystoreply.gearbox.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "app_user")
data class UserEntity(
    @Id
    val id: String,
    val email: String,
    val username: String,
    val password: String,
    val profileImageUrl: String?
) {
    fun toDomain(): User = User(
        id = id,
        email = email,
        username = username,
        password = password,
        profileImageUrl = profileImageUrl
    )

    companion object {
        fun fromDomain(user: User): UserEntity = UserEntity(
            id = user.id,
            email = user.email,
            username = user.username,
            password = user.password,
            profileImageUrl = user.profileImageUrl,
        )
    }
}
