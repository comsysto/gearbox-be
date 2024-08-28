package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.stereotype.Component

@Component
class UserSignUpUseCase(private val userApi: UserApiFacade) {
    fun execute(request: AuthenticationRequestDto): UserEntity {
        return UserEntity.fromDomain(userApi.signUp(request.toDomain()))
    }
}