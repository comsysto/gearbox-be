package de.comsystoreply.gearbox.bff.usecase.auth

import de.comsystoreply.gearbox.bff.dto.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationService
import de.comsystoreply.gearbox.domain.user.port.application.UserService
import org.springframework.stereotype.Component

@Component
class UserSignUpUseCase(
    private val authenticationService: AuthenticationService,
    private val userService: UserService
) {
    fun execute(authenticationRequestDto: AuthenticationRequestDto): User {
        val user = userService.findByEmailAndPassword(authenticationRequestDto.email, authenticationRequestDto.password)

        if (user != null) {
            throw IllegalStateException("User already exists")
        }
        // TODO: Save image

        val domainObject = authenticationRequestDto.toDomain(null)
        return authenticationService.signUp(domainObject)
    }
}