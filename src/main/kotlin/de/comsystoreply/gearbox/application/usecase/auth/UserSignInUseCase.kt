package de.comsystoreply.gearbox.application.usecase.auth

import de.comsystoreply.gearbox.application.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationApiFacade
import org.springframework.stereotype.Component

@Component
class UserSignInUseCase(private val authenticationApiFacade: AuthenticationApiFacade) {
    fun execute(request: AuthenticationRequestDto): User =
        authenticationApiFacade.signIn(request.email, request.password)
}