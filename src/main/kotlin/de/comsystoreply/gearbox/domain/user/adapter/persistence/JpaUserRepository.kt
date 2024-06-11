package de.comsystoreply.gearbox.domain.user.adapter.persistence

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.model.persistance.UserEntity
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class JpaUserRepository(private val jpaUserEntityRepository: JpaUserEntityRepository) : UserRepository {
    override fun findByEmailAndPassword(email: String, password: String): User? {
        return jpaUserEntityRepository.findByEmailAndPassword(email, password)?.toDomain()
    }

    override fun create(user: User): User = jpaUserEntityRepository.save(UserEntity.fromDomain(user)).toDomain()

    override fun getAll(): List<User> = jpaUserEntityRepository.findAll().map { it.toDomain() }
}

interface JpaUserEntityRepository : JpaRepository<UserEntity, Long> {
    fun findByEmailAndPassword(email: String, password: String): UserEntity?
}