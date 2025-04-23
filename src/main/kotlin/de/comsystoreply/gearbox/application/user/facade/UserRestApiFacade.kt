package de.comsystoreply.gearbox.application.user.facade

import de.comsystoreply.gearbox.application.user.port.web.UserResponseDto
import de.comsystoreply.gearbox.application.user.port.web.UserWebFacade
import de.comsystoreply.gearbox.application.user.usecase.SearchUsersUseCase
import de.comsystoreply.gearbox.application.user.usecase.UploadProfileImageUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserRestApiFacade(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase
) : UserWebFacade {
    override fun search(query: String, pageable: Pageable): Page<UserResponseDto> =
        searchUsersUseCase.execute(query, pageable).map { UserResponseDto.fromEntity(it) }

    override fun uploadProfileImage(userId: String, file: MultipartFile): UserResponseDto {
        val userEntity = uploadProfileImageUseCase.execute(userId, file)
        return UserResponseDto.fromEntity(userEntity)
    }
}