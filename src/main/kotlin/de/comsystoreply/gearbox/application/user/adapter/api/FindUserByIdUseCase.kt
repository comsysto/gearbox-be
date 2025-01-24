package de.comsystoreply.gearbox.application.user.adapter.api

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.stereotype.Component

@Component
class FindUserByIdUseCase(private val userApiFacade: UserApiFacade) {
    fun execute(id: String): UserEntity {
        val user = userApiFacade.findById(id)
        return UserEntity.fromDomain(user)
    }
}