package de.comsystoreply.gearbox.application.user.port.web

import de.comsystoreply.gearbox.application.user.model.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Interface which defines methods that expose User manipulation features via REST API
 */
interface UserWebFacade {
    /**
     * Function gets the query string from the user and returns list of users that match the given query
     * @property [query] search criteria
     * @property [pageable] simple page request
     * @return returns the pageable list of [UserResponseDto] that match the [query] search criteria
     */
    fun search(query: String, pageable: Pageable): Page<UserResponseDto>
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