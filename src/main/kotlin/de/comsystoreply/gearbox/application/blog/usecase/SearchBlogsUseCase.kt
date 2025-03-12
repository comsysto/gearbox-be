package de.comsystoreply.gearbox.application.blog.usecase

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class SearchBlogsUseCase(private val blogApi: BlogApiFacade) {
    fun execute(query: String, pageable: Pageable): Page<BlogEntity> {
        return blogApi
            .search(query, pageable)
            .map { BlogEntity.fromDomain(it) }
    }
}