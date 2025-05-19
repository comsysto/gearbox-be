package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component

@Component
class GetTokenOwnerIdUseCase(
    private val userApiFacade: UserApiFacade
) {
    fun execute(): String {
        val email = SecurityContextHolder.getContext().authentication.name
        val tokenOwner = userApiFacade.findByEmail(email)
        return tokenOwner.id
    }
}