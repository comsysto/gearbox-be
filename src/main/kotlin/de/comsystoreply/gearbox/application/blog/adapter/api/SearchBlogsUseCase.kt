package de.comsystoreply.gearbox.application.blog.adapter.api

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.stereotype.Component

@Component
class SearchBlogsUseCase(private val blogApi: BlogApiFacade) {
    fun execute(query: String): List<BlogEntity> {
        return blogApi.search(query).map { BlogEntity.fromDomain(it) }
    }
}