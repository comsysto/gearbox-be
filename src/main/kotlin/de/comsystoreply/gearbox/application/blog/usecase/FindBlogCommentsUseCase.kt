package de.comsystoreply.gearbox.application.blog.usecase

import de.comsystoreply.gearbox.application.blog.model.CommentEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
final class FindBlogCommentsUseCase(private val blogApi: BlogApiFacade) {
    final fun execute(blogId: String, pageable: Pageable = PageRequest.of(0, 10)): Page<CommentEntity> {
        return blogApi
            .findBlogComments(blogId, pageable)
            .map { CommentEntity.fromDomain(it) }
    }
}