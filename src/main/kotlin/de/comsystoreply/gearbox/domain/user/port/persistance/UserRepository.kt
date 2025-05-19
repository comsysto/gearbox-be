package de.comsystoreply.gearbox.domain.user.port.persistance

import de.comsystoreply.gearbox.domain.user.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable


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
     * @property [id] provided by user, unique String
     * @return If exists, returns User else returns null
     */
    fun findById(id: String): User?

    /**
     * @property [query] search criteria, simple string
     * @property [pageable] defines which page and size should return
     * @return Pageable list of User objects whose usernames match the search criteria
     */
    fun search(query: String, pageable: Pageable): Page<User>

    /**
     * @property [user] new User object for creation
     * @return The newly created User
     */
    fun create(user: User): User

    /**
     * @return A list of all users
     */
    fun getAll(): List<User>

    /**
     * @property [user] object with new properties ready to save
     * @return User domain object with updated properties
     */
    fun save(user: User): User
}