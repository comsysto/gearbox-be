package de.comsystoreply.gearbox.application.user.facade

import de.comsystoreply.gearbox.application.user.port.web.UserResponseDto
import de.comsystoreply.gearbox.application.user.port.web.UserWebFacade
import de.comsystoreply.gearbox.application.user.usecase.GetTokenOwnerIdUseCase
import de.comsystoreply.gearbox.application.user.usecase.FindUserByIdUseCase
import de.comsystoreply.gearbox.application.user.usecase.SearchUsersUseCase
import de.comsystoreply.gearbox.application.user.usecase.UploadProfileImageUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UserRestApiFacade(
    private val searchUsersUseCase: SearchUsersUseCase,
    private val uploadProfileImageUseCase: UploadProfileImageUseCase,
    private val findUserByIdUseCase: FindUserByIdUseCase,
    private val getTokenOwnerIdUseCase: GetTokenOwnerIdUseCase
) : UserWebFacade {
    override fun search(query: String, pageable: Pageable): Page<UserResponseDto> =
        searchUsersUseCase.execute(query, pageable).map { UserResponseDto.fromEntity(it) }

    override fun getProfileData(id: String): UserResponseDto {
        val userResult = findUserByIdUseCase.execute(id)
        val tokenOwnerId = getTokenOwnerIdUseCase.execute()

        return UserResponseDto.fromEntity(userResult, tokenOwnerId == id)
    }

    override fun uploadProfileImage(profileUserId: String, file: MultipartFile): UserResponseDto {
        val actorUserId = getTokenOwnerIdUseCase.execute()
        val userEntity = uploadProfileImageUseCase.execute(actorUserId, profileUserId, file)
        return UserResponseDto.fromEntity(userEntity, true)
    }
}