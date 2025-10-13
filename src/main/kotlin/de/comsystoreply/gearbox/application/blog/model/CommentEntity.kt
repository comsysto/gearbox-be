package de.comsystoreply.gearbox.application.blog.model

import de.comsystoreply.gearbox.domain.blog.model.Comment
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "comment")
data class CommentEntity(
    @Id
    val id: String,

    @Column(nullable = false)
    val blogId: String,

    @Column(nullable = false)
    val userId: String,

    @Column(columnDefinition = "TEXT", nullable = false)
    val content: String
) {
    fun toDomain(): Comment {
        return Comment(
            id = id,
            blogId = blogId,
            userId = userId,
            content = content,
        )
    }

    companion object {
        fun fromDomain(comment: Comment): CommentEntity {
            return CommentEntity(
                id = comment.id,
                blogId = comment.blogId,
                userId = comment.userId,
                content = comment.content,
            )
        }
    }
}