package de.comsystoreply.gearbox.application.blog.usecase

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class FindTrendingBlogsUseCase(private val blogApi: BlogApiFacade) {
    fun execute(pageable: Pageable): Page<BlogEntity> {
        return blogApi
            .findTrending(pageable)
            .map { BlogEntity.fromDomain(it) }
    }
}