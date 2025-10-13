package de.comsystoreply.gearbox.application.blog.adapter.persistance

import de.comsystoreply.gearbox.application.blog.model.CommentEntity
import de.comsystoreply.gearbox.domain.blog.model.Comment
import de.comsystoreply.gearbox.domain.blog.port.persistance.CommentRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
class JpaCommentRepository(
    private val commentRepository: JpaCommentEntityRepository
) : CommentRepository {
    override fun save(comment: Comment): Comment {
        return commentRepository.save(CommentEntity.fromDomain(comment)).toDomain()
    }

    override fun findAllByBlogId(blogId: String, pageable: Pageable): Page<Comment> {
        return commentRepository.findAllByBlogId(blogId, pageable).map { it.toDomain() }
    }
}

interface JpaCommentEntityRepository : JpaRepository<CommentEntity, String> {
    fun findAllByBlogId(blogId: String, pageable: Pageable): Page<CommentEntity>
}