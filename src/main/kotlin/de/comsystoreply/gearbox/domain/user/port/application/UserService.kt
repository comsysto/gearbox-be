package de.comsystoreply.gearbox.domain.user.port.application

import de.comsystoreply.gearbox.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * User service interface which provides user search and fetching
 */
interface UserService {
    /**
     * @property [email] provided by user, must be valid email address
     * @property [password] provided by user
     * @return If exists, returns User, else returns null
     */
    fun findByEmailAndPassword(email: String, password: String): User?
}