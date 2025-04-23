package de.comsystoreply.gearbox.application.user.adapter.persistence

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(
    private val jpaUserEntityRepository: JpaUserEntityRepository,
    private val encoder: PasswordEncoder,
) : UserRepository {
    override fun findByEmailAndPassword(email: String, password: String): User? {
        val user = jpaUserEntityRepository.findByEmail(email)
        if (user != null && encoder.matches(password, user.password)) {
            return user.toDomain()
        }
        return null
    }

    override fun findByEmail(email: String): User? {
        return jpaUserEntityRepository.findByEmail(email)?.toDomain()
    }

    override fun findByUsername(username: String): User? {
        return jpaUserEntityRepository.findByUsername(username)?.toDomain()
    }

    override fun findById(id: String): User? {
        return jpaUserEntityRepository.findById(id)?.toDomain()
    }

    override fun search(query: String, pageable: Pageable): Page<User> {
        return jpaUserEntityRepository.findAllByUsernameContainingIgnoreCase(query, pageable).map { it.toDomain() }
    }

    override fun create(user: User): User {
        val entity = UserEntity.fromDomain(user)
        val encoded = entity.copy(password = encoder.encode(user.password))
        return jpaUserEntityRepository.save(encoded).toDomain()
    }

    override fun getAll(): List<User> = jpaUserEntityRepository.findAll().map { it.toDomain() }

    override fun save(user: User): User {
        val entity = UserEntity.fromDomain(user)
        return jpaUserEntityRepository.save(entity).toDomain()
    }
}

interface JpaUserEntityRepository : JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
    fun findByUsername(username: String): UserEntity?
    fun findById(id: String): UserEntity?
    fun findAllByUsernameContainingIgnoreCase(username: String, pageable: Pageable): Page<UserEntity>
}