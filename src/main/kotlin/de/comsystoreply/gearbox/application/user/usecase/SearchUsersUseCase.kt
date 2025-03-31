package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class SearchUsersUseCase(private val userApiFacade: UserApiFacade) {
    fun execute(query: String, pageable: Pageable): Page<UserEntity> =
        userApiFacade.search(query, pageable).map { UserEntity.fromDomain(it) }
}