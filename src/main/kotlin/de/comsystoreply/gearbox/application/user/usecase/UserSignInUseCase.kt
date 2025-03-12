package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.stereotype.Component

@Component
class UserSignInUseCase(private val userApi: UserApiFacade) {
    fun execute(request: AuthenticationRequestDto): UserEntity {
        return UserEntity.fromDomain(userApi.signIn(request.toDomain()))
    }
}