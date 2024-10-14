package de.comsystoreply.gearbox.domain.blog.service

import de.comsystoreply.gearbox.domain.blog.model.Blog
import de.comsystoreply.gearbox.domain.blog.port.api.*
import de.comsystoreply.gearbox.domain.blog.port.persistance.BlogRepository
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.temporal.TemporalAdjusters

@Service
class BlogService(
    private val blogRepository: BlogRepository,
    private val userRepository: UserRepository,
) : BlogApiFacade {
    override fun findTrending(pageable: Pageable): Page<Blog> {
        val startOfWeek = LocalDateTime.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .with(LocalTime.MIN)
        val endOfWeek = startOfWeek.plusWeeks(1).with(LocalTime.MAX)

        return blogRepository.findTrending(startOfWeek, endOfWeek, pageable)
    }

    override fun findLatest(pageable: Pageable): Page<Blog> {
        return blogRepository.findLatest(pageable)
    }

    override fun findByAuthor(userId: String, pageable: Pageable): Page<Blog> {
        userRepository.findById(userId) ?: throw BlogUserNotFoundException("User is not found.")
        return blogRepository.findByAuthor(userId, pageable)
    }

    override fun findLikedBy(userId: String, pageable: Pageable): Page<Blog> {
        userRepository.findById(userId) ?: throw BlogUserNotFoundException("User is not found.")
        return blogRepository.findLikedBy(userId, pageable)
    }

    override fun search(query: String, pageable: Pageable): Page<Blog> {
        return blogRepository.search(query, pageable)
    }

    override fun toggleLike(blogId: String, userId: String) {
        userRepository.findById(userId) ?: throw BlogUserNotFoundException("User is not found.")
        val blog = blogRepository.findById(blogId) ?: throw BlogNotFoundException("Blog is not found.")
        var likeCount = blog.numberOfLikes

        if (blogRepository.isBlogLikedBy(blogId, userId)) {
            blogRepository.removeLike(blogId, userId)
            likeCount--
        } else {
            blogRepository.like(blogId, userId)
            likeCount++
        }

        blogRepository.updateLikeCount(blogId, likeCount)
    }
}