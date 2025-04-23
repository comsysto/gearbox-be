package de.comsystoreply.gearbox.application.user.port.web

import de.comsystoreply.gearbox.application.user.model.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.web.multipart.MultipartFile

/**
 * Interface which defines methods that expose User manipulation features via REST API
 */
interface UserWebFacade {
    /**
     * Function gets the query string from the user and returns list of users that match the given query
     * @property [query] search criteria
     * @property [pageable] simple page request
     * @return Pageable list of [UserResponseDto] that match the [query] search criteria
     */
    fun search(query: String, pageable: Pageable): Page<UserResponseDto>

    /**
     * Function gets the image with userId from the user returns the user with the updated profile image url
     * @property [userId] user identifier
     * @property [file] image file ready to set as profile image
     * @return Updated [UserResponseDto] with new profile image url property
     */
    fun uploadProfileImage(userId: String, file: MultipartFile): UserResponseDto
}

data class UserResponseDto(
    val id: String,
    val username: String,
    val profileImageUrl: String?
) {
    companion object {
        fun fromEntity(entity: UserEntity): UserResponseDto {
            return UserResponseDto(
                id = entity.id,
                username = entity.username,
                profileImageUrl = entity.profileImageUrl
            )
        }
    }
}