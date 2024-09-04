package de.comsystoreply.gearbox.application.blog.adapter.api

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.stereotype.Component

@Component
class FindLatestBlogsUseCase(private val blogApiFacade: BlogApiFacade) {
    fun execute(): List<BlogEntity> {
        return blogApiFacade.findLatest().map { BlogEntity.fromDomain(it) }
    }
}