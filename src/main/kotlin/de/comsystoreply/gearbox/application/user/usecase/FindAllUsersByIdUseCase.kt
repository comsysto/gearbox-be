package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.stereotype.Component

@Component
final class FindAllUsersByIdUseCase(private val userApiFacade: UserApiFacade) {
    final fun execute(userIds: List<String>): List<UserEntity> {
        return userApiFacade
            .findAllById(userIds)
            .map { UserEntity.fromDomain(it) }
    }
}