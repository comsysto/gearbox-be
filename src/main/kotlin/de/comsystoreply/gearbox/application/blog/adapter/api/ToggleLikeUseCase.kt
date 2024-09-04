package de.comsystoreply.gearbox.application.blog.adapter.api

import de.comsystoreply.gearbox.domain.blog.port.api.BlogApiFacade
import org.springframework.stereotype.Component

@Component
class ToggleLikeUseCase(private val blogApi: BlogApiFacade) {
    fun execute(blogId: String, userId: String) = blogApi.toggleLike(blogId, userId)
}