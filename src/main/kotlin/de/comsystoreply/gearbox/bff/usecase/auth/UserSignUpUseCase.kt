package de.comsystoreply.gearbox.bff.usecase.auth

import de.comsystoreply.gearbox.bff.dto.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationService
import org.springframework.stereotype.Component

@Component
class UserSignUpUseCase(private val authenticationService: AuthenticationService) {
    fun execute(authenticationRequestDto: AuthenticationRequestDto): User {
        // TODO: Save image
        val domainObject = authenticationRequestDto.toDomain(null)
        return authenticationService.signUp(domainObject)
    }
}