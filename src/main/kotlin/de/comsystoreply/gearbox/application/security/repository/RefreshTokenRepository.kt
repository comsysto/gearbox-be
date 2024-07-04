package de.comsystoreply.gearbox.application.security.repository

import de.comsystoreply.gearbox.application.user.model.UserEntity
import org.springframework.stereotype.Component

@Component
class RefreshTokenRepository {

    private val tokens = mutableMapOf<String, UserEntity>()

    fun findUserDetailsByToken(token: String): UserEntity? = tokens[token]

    fun save(token: String, userDetails: UserEntity) {
        tokens[token] = userDetails
    }
}