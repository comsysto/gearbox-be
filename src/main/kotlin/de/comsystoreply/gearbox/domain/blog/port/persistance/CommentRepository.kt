package de.comsystoreply.gearbox.domain.blog.port.persistance

import de.comsystoreply.gearbox.domain.blog.model.Comment
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable

/**
 * Basic Comment repository which provides Comment domain object operations in the datasource
 */
interface CommentRepository {
    fun save(comment: Comment): Comment
    fun findAllByBlogId(blogId: String, pageable: Pageable): Page<Comment>
}