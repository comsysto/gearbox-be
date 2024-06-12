package de.comsystoreply.gearbox.domain.user.domain

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserApiFacade {
    override fun findByEmailAndPassword(email: String, password: String): User? {
        return userRepository.findByEmailAndPassword(email, password)
    }
}