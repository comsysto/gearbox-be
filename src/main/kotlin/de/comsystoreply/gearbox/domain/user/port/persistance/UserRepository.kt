package de.comsystoreply.gearbox.domain.user.port.persistance

import de.comsystoreply.gearbox.domain.user.model.User


/**
 * Basic User repository which provides User domain object operations in the datasource
 */
interface UserRepository {
    /**
     * @property [email] provided by user, must be valid email address
     * @property [password] provided by user
     * @return If exists, returns User else returns null
     */
    fun findByEmailAndPassword(email: String, password: String): User?

    /**
     * @property [email] provided by user, must be valid email address
     * @return If exists, returns User else returns null
     */
    fun findByEmail(email: String): User?

    /**
     * @property [username] provided by user, simple String
     * @return If exists, returns User else returns null
     */
    fun findByUsername(username: String): User?

    /**
     * @property [user] new User object for creation
     * @return Returns the newly created User
     */
    fun create(user: User): User

    /**
     * @return A list of all users
     */
    fun getAll(): List<User>
}