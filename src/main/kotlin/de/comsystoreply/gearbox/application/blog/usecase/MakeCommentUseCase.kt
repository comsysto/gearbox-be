package de.comsystoreply.gearbox.application.blog.usecase

import de.comsystoreply.gearbox.application.blog.model.CommentEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
final class MakeCommentUseCase(private val blogApiFacade: BlogApiFacade) {
    final fun execute(blogId: String, userId: String, content: String): Page<CommentEntity> {
        return blogApiFacade
            .makeComment(blogId, userId, content)
            .map { CommentEntity.fromDomain(it) }
    }
}