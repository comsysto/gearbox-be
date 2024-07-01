package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationApiFacade
import org.springframework.stereotype.Component

@Component
class UserSignInUseCase(private val authenticationApiFacade: AuthenticationApiFacade) {
    fun execute(request: AuthenticationRequestDto): UserEntity {
        return UserEntity.fromDomain(authenticationApiFacade.signIn(request.toDomain()))
    }
}