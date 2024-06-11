package de.comsystoreply.gearbox.bff.usecase.auth

import de.comsystoreply.gearbox.bff.dto.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationService
import org.springframework.stereotype.Component

@Component
class UserSignInUseCase(private val authenticationService: AuthenticationService) {
    fun execute(request: AuthenticationRequestDto): User? {
        return authenticationService.signIn(request.email, request.password)
    }
}