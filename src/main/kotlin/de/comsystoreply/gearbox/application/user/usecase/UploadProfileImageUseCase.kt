package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class UploadProfileImageUseCase(private val userApi: UserApiFacade) {
    fun execute(actorUserId: String, profileUserId: String, image: MultipartFile): UserEntity {
        val user = userApi.setProfileImage(actorUserId, profileUserId, image.bytes, image.contentType)
        return UserEntity.fromDomain(user)
    }
}