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
     * Function gets the user id and returns the complete user data for the given id
     * @property [id] user identifier
     * @return [UserResponseDto] for the given id
     */
    fun getProfileData(id: String): UserResponseDto

    /**
     * Function gets the image with userId from the user and returns the user with the updated profile image url.
     * @property [profileUserId] user id for profile
     * @property [file] image file ready to set as profile image
     * @return Updated [UserResponseDto] with new profile image url property
     */
    fun uploadProfileImage(profileUserId: String, file: MultipartFile): UserResponseDto
}

data class UserResponseDto(
    val id: String,
    val username: String,
    val profileImageUrl: String?,
    val isProfileOwner: Boolean = false,
) {
    companion object {
        fun fromEntity(entity: UserEntity, isProfileOwner: Boolean = false): UserResponseDto {
            return UserResponseDto(
                id = entity.id,
                username = entity.username,
                profileImageUrl = entity.profileImageUrl,
                isProfileOwner = isProfileOwner
            )
        }
    }
}