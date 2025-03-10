package de.comsystoreply.gearbox.application.blog.usecase

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class FindLatestBlogsUseCase(private val blogApiFacade: BlogApiFacade) {
    fun execute(pageable: Pageable): Page<BlogEntity> {
        return blogApiFacade
            .findLatest(pageable)
            .map { BlogEntity.fromDomain(it) }
    }
}