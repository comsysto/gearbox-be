package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationApiFacade
import org.springframework.stereotype.Component

@Component
class UserSignUpUseCase(private val authenticationApiFacade: AuthenticationApiFacade) {
    fun execute(request: AuthenticationRequestDto): UserEntity {
        // TODO: Save image
        return UserEntity.fromDomain(authenticationApiFacade.signUp(request.toDomain()))
    }
}