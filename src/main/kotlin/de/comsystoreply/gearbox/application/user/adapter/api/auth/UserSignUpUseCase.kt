package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationApiFacade
import org.springframework.stereotype.Component

@Component
class UserSignUpUseCase(private val authenticationApiFacade: AuthenticationApiFacade) {
    fun execute(authenticationRequestDto: AuthenticationRequestDto): User {
        // TODO: Save image
        val domainObject = authenticationRequestDto.toDomain(null)
        return authenticationApiFacade.signUp(domainObject)
    }
}