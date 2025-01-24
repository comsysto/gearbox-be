package de.comsystoreply.gearbox.domain.blog.port.persistance

import de.comsystoreply.gearbox.domain.blog.model.Blog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import java.time.LocalDateTime

/**
 * Basic Blog repository which provides Blog domain object operations in the datasource
 */
interface BlogRepository {

    /**
     * @property [id] unique identifier of the Blog
     * @return If exists, returns Blog else returns null
     */
    fun findById(id: String): Blog?

    /**
     * @property [startOfWeek] the date of start of the week for which to find trending blogs
     * @property [endOfWeek] the date of end of the week for which to find trending blogs
     * @property [pageable] defines which page and size should return
     * @return A list of trending Blog objects within the specified time frame
     */
    fun findTrending(startOfWeek: LocalDateTime, endOfWeek: LocalDateTime, pageable: Pageable): Page<Blog>

    /**
     * @property [pageable] defines which page and size should return
     * @return A list of the latest Blog objects, ordered by creation date
     */
    fun findLatest(pageable: Pageable): Page<Blog>

    /**
     * @property [userId] unique identifier of the author (User)
     * @property [pageable] defines which page and size should return
     * @return A list of Blog objects authored by the specified user
     */
    fun findByAuthor(userId: String, pageable: Pageable): Page<Blog>

    /**
     * @property [userId] unique identifier of the user
     * @property [pageable] defines which page and size should return
     * @return A list of Blog objects liked by the specified user
     */
    fun findLikedBy(userId: String, pageable: Pageable): Page<Blog>

    /**
     * @property [blogId] unique identifier of the Blog
     * @property [userId] unique identifier of the user
     * @return True if the specified blog is liked by the specified user, else false
     */
    fun isBlogLikedBy(blogId: String, userId: String): Boolean

    /**
     * @property [query] a String used to search blogs based on title or content
     * @property [pageable] defines which page and size should return
     * @return A list of Blog objects matching the search query
     */
    fun search(query: String, pageable: Pageable): Page<Blog>

    /**
     * @property [blogId] unique identifier of the Blog
     * @property [userId] unique identifier of the user
     * Adds a like to the specified blog from the specified user
     */
    fun like(blogId: String, userId: String)

    /**
     * @property [blogId] unique identifier of the Blog
     * @property [userId] unique identifier of the user
     * Removes a like from the specified blog for the specified user
     */
    fun removeLike(blogId: String, userId: String)

    /**
     * @property [blogId] unique identifier of the Blog
     * @property [likeCount] new count of likes
     * Updates the like count for the specified blog
     */
    fun updateLikeCount(blogId: String, likeCount: Int)
}