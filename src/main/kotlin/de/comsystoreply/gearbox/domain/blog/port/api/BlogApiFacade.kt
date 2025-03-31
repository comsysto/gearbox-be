package de.comsystoreply.gearbox.domain.blog.port.api

import de.comsystoreply.gearbox.domain.blog.model.Blog
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Blog API interface which provides blog CRUD operations and more
 */
interface BlogApiFacade {
    /**
     * @property [pageable] defines page object that contains size and page
     * @return returns the list of 5 most liked blogs in the currentWeek
     */
    fun findTrending(pageable: Pageable): Page<Blog>

    /**
     * @property [pageable] defines page object that contains size and page
     * @return returns the list of 20 most recent blogs
     */
    fun findLatest(pageable: Pageable): Page<Blog>

    /**
     * @property [userId] user unique identifier
     * @property [pageable] defines page object that contains size and page
     * @return returns the list of blogs written by desired user
     * @throws BlogUserNotFoundException if author with given [userId] is not found
     */
    fun findByAuthor(userId: String, pageable: Pageable): Page<Blog>

    /**
     * @property [userId] user unique identifier
     * @property [pageable] defines page object that contains size and page
     * @return returns the list of blogs liked by desired user
     * @throws BlogUserNotFoundException if author with given [userId] is not found
     */
    fun findLikedBy(userId: String, pageable: Pageable): Page<Blog>

    /**
     * @property [query] string query to search blog title
     * @property [pageable] defines page object that contains size and page
     * @return returns the pageable list of blogs with title that matches the search criteria
     */
    fun search(query: String, pageable: Pageable): Page<Blog>

    /**
     * @property [blogId] blog unique identifier
     * @property [userId] user unique identifier
     * Toggles the blog like state with [blogId] by the user with [userId]
     * @throws BlogUserNotFoundException if author with given [userId] is not found
     * @throws BlogNotFoundException if blog with given [blogId] is not found
     */
    fun toggleLike(blogId: String, userId: String)
}

sealed class BlogException(message: String) : Exception(message)

class BlogUserNotFoundException(message: String) : BlogException(message)

class BlogNotFoundException(message: String) : BlogException(message)