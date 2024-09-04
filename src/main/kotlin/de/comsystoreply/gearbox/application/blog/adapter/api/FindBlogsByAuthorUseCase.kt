package de.comsystoreply.gearbox.application.blog.adapter.api

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.stereotype.Component

@Component
class FindBlogsByAuthorUseCase(private val blogApi: BlogApiFacade) {
    fun execute(userId: String): List<BlogEntity> {
        return blogApi.findByAuthor(userId).map { BlogEntity.fromDomain(it) }
    }
}