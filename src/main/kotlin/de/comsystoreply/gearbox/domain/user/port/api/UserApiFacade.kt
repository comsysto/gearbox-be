package de.comsystoreply.gearbox.domain.user.port.api

import de.comsystoreply.gearbox.domain.user.model.User

/**
 * User API interface which provides user search and fetching
 */
interface UserApiFacade {
    /**
     * @property [email] provided by user, must be valid email address
     * @property [password] provided by user
     * @return If exists, returns User, else returns null
     */
    fun findByEmailAndPassword(email: String, password: String): User?
}