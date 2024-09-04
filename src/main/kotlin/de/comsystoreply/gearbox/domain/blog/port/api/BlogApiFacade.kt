package de.comsystoreply.gearbox.domain.blog.port.api

import de.comsystoreply.gearbox.domain.blog.model.Blog

/**
 * Blog API interface which provides blog CRUD operations and more
 */
interface BlogApiFacade {
    /**
     * @return returns the list of 5 most liked blogs in the currentWeek
     */
    fun findTrending(): List<Blog>

    /**
     * @return returns the list of 20 most recent blogs
     */
    fun findLatest(): List<Blog>

    /**
     * @property [userId] user unique identifier
     * @return returns the list of blogs written by desired user
     * @throws BlogUserNotFoundException if author with given [userId] is not found
     */
    fun findByAuthor(userId: String): List<Blog>

    /**
     * @property [userId] user unique identifier
     * @return returns the list of blogs liked by desired user
     * @throws BlogUserNotFoundException if author with given [userId] is not found
     */
    fun findLikedBy(userId: String): List<Blog>

    /**
     * @property [query] string query to search blog title
     * @return returns the list of blogs that match the query
     */
    fun search(query: String): List<Blog>

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