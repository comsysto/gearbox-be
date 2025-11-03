package de.comsystoreply.gearbox.application.blog.port.web

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.application.blog.model.CommentEntity
import de.comsystoreply.gearbox.application.user.model.UserEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

/**
 * Interface which defines methods which expose Blog features via REST API
 */
interface BlogWebFacade {
    /**
     * Function returns list of trending blogs
     * @return the pageable list of [BlogResponseDto]
     */
    fun findTrending(pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of latest blogs
     * @return the pageable list of [BlogResponseDto]
     */
    fun findLatest(pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of blogs whose author is user with given id
     * @property [userId] is user's unique identifier
     * @property [pageable] is simple page request
     * @return the pageable list of [BlogResponseDto]
     */
    fun findByAuthor(userId: String, pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of blogs liked by user with given id
     * @property [userId] is user's unique identifier
     * @property [pageable] is simple page request
     * @return the pageable list of [BlogResponseDto]
     */
    fun findLikedBy(userId: String, pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function returns list of blogs which
     * @property [query] simple search query
     * @property [pageable] simple page request
     * @return the pageable list of [BlogResponseDto] that match the [query] search criteria
     */
    fun search(query: String, pageable: Pageable): Page<BlogResponseDto>

    /**
     * Function toggles the blog like state for the blog by the user
     * @property [likeRequestDto] contains blog unique identifier and user unique identifier
     */
    fun toggleLike(likeRequestDto: LikeRequestDto)

    /**
     * Function adds new comment to the blog
     * @property [commentRequestDto] contains blogId, authorId and content
     * @return the pageable list of [CommentResponseDto] with new comment
     */
    fun makeComment(commentRequestDto: CommentRequestDto): Page<CommentResponseDto>

    /**
     * Function returns list of comments for the blog
     * @property [blogId] is blog's unique identifier
     * @property [pageable] is simple page request
     * @return the pageable list of [CommentResponseDto]
     */
    fun findCommentsForBlog(blogId: String, pageable: Pageable): Page<CommentResponseDto>
}

data class BlogResponseDto(
    val id: String,
    val title: String,
    val content: String,
    val thumbnailImageUrl: String,
    val createDate: LocalDateTime,
    val numberOfLikes: Int,
    var numberOfComments: Int,
    val category: String,
    val author: AuthorResponseDto,
) {
    companion object {
        fun fromEntity(blog: BlogEntity, user: AuthorResponseDto): BlogResponseDto {
            return BlogResponseDto(
                blog.id,
                blog.title,
                blog.content,
                blog.thumbnailImageUrl,
                blog.createDate,
                blog.numberOfLikes,
                0,
                blog.category.name,
                user
            )
        }
    }
}

data class AuthorResponseDto(
    val id: String,
    val username: String,
    val profileImageUrl: String?
) {
    companion object {
        fun fromEntity(user: UserEntity): AuthorResponseDto {
            return AuthorResponseDto(
                user.id,
                user.username,
                user.profileImageUrl
            )
        }
    }
}

data class LikeRequestDto(
    val blogId: String,
    val userId: String
)

data class CommentRequestDto(
    val blogId: String,
    val userId: String,
    val content: String,
)

data class CommentResponseDto(
    val id: String,
    val blogId: String,
    val userId: String,
    val username: String,
    val userProfileImageUrl: String?,
    val content: String,
) {
    companion object {
        fun fromEntity(comment: CommentEntity, username: String, profileImage: String?): CommentResponseDto {
            return CommentResponseDto(
                comment.id,
                comment.blogId,
                comment.userId,
                username,
                profileImage,
                comment.content,
            )
        }
    }
}