package de.comsystoreply.gearbox.application.user.usecase

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorage
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class UploadProfileImageUseCase(
    private val cloudImageStorage: CloudImageStorage,
    private val userApi: UserApiFacade
) {
    fun execute(userId: String, image: MultipartFile): UserEntity {
        val user = userApi.findById(userId)

        val imageUrl = cloudImageStorage.uploadImage(image)
        user.profileImageUrl = imageUrl
        userApi.save(user)

        return UserEntity.fromDomain(user)
    }
}