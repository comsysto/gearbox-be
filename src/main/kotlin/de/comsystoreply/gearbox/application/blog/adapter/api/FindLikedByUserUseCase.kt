package de.comsystoreply.gearbox.application.blog.adapter.api

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Component

@Component
class FindLikedByUserUseCase(private val blogApi: BlogApiFacade) {
    fun execute(userId: String, pageable: Pageable): Page<BlogEntity> {
        return blogApi
            .findLikedBy(userId, pageable)
            .map { BlogEntity.fromDomain(it) }
    }
}