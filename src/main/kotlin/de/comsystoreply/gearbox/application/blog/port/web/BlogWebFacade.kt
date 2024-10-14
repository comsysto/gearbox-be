package de.comsystoreply.gearbox.application.blog.port.web

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

/**
 * Interface which defines methods which expose Blog features via REST API
 */
interface BlogWebFacade {
    /**
     * Function returns list of trending blogs
     * @return list of [BlogResponseDto]
     */
    fun findTrending(pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of latest blogs
     * @return list of [BlogResponseDto]
     */
    fun findLatest(pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of blogs whose author is user with given id
     * @property [userId] is user's unique identifier
     * @return list of [BlogResponseDto]
     */
    fun findByAuthor(userId: String, pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of blogs liked by user with given id
     * @property [userId] is user's unique identifier
     * @return list of [BlogResponseDto]
     */
    fun findLikedBy(userId: String, pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of blogs which
     * @property [query] simple search query
     * @return returns the list of [BlogResponseDto] that match the query
     */
    fun search(query: String, pageable: Pageable): Page<BlogResponseDto>

    /**
     * @property [likeRequestDto] which contains blog unique identifier and user unique identifier
     * Toggles the blog like state for the blog by the user
     */
    fun toggleLike(likeRequestDto: LikeRequestDto)
}

data class BlogResponseDto(
    val id: String,
    val title: String,
    val content: String,
    val thumbnailImageUrl: String,
    val userId: String,
    val createDate: LocalDateTime,
    var numberOfLikes: Int,
    val category: String
) {
    companion object {
        fun fromEntity(blog: BlogEntity): BlogResponseDto {
            return BlogResponseDto(
                blog.id,
                blog.title,
                blog.content,
                blog.thumbnailImageUrl,
                blog.userId,
                blog.createDate,
                blog.numberOfLikes,
                blog.category.name
            )
        }
    }
}

data class LikeRequestDto(
    val blogId: String,
    val userId: String
)

data class SearchQueryDto(val query: String, val page: Int, val pageSize: Int)