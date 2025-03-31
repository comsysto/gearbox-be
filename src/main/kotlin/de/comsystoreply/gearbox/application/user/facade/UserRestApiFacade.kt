package de.comsystoreply.gearbox.application.user.facade

import de.comsystoreply.gearbox.application.user.port.web.UserResponseDto
import de.comsystoreply.gearbox.application.user.port.web.UserWebFacade
import de.comsystoreply.gearbox.application.user.usecase.SearchUsersUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class UserRestApiFacade(
    private val searchUsersUseCase: SearchUsersUseCase
) : UserWebFacade {
    override fun search(query: String, pageable: Pageable): Page<UserResponseDto> =
        searchUsersUseCase.execute(query, pageable).map { UserResponseDto.fromEntity(it) }
}