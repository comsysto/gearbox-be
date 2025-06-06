package de.comsystoreply.gearbox.application.blog.facade

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.application.blog.port.web.BlogResponseDto
import de.comsystoreply.gearbox.application.blog.port.web.BlogWebFacade
import de.comsystoreply.gearbox.application.blog.port.web.LikeRequestDto
import de.comsystoreply.gearbox.application.blog.port.web.AuthorResponseDto
import de.comsystoreply.gearbox.application.blog.usecase.*
import de.comsystoreply.gearbox.application.user.usecase.FindUserByIdUseCase
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BlogRestApiFacade(
    private val findTrendingBlogsUseCase: FindTrendingBlogsUseCase,
    private val findLatestBlogsUseCase: FindLatestBlogsUseCase,
    private val findUserByIdUseCase: FindUserByIdUseCase,
    private val findBlogsByAuthorUseCase: FindBlogsByAuthorUseCase,
    private val findBlogsLikedByUserUseCase: FindLikedByUserUseCase,
    private val searchBlogUseCase: SearchBlogsUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
) : BlogWebFacade {
    override fun findTrending(pageable: Pageable): Page<BlogResponseDto> {
        return findTrendingBlogsUseCase
            .execute(pageable)
            .map { mapBlogWithAuthor(it) }
    }

    override fun findLatest(pageable: Pageable): Page<BlogResponseDto> {
        return findLatestBlogsUseCase
            .execute(pageable)
            .map { mapBlogWithAuthor(it) }
    }

    override fun findByAuthor(userId: String, pageable: Pageable): Page<BlogResponseDto> {
        return findBlogsByAuthorUseCase
            .execute(userId, pageable)
            .map { mapBlogWithAuthor(it) }
    }

    override fun findLikedBy(userId: String, pageable: Pageable): Page<BlogResponseDto> {
        return findBlogsLikedByUserUseCase
            .execute(userId, pageable)
            .map { mapBlogWithAuthor(it) }
    }

    override fun search(query: String, pageable: Pageable): Page<BlogResponseDto> {
        return searchBlogUseCase
            .execute(query, pageable)
            .map { mapBlogWithAuthor(it) }
    }

    override fun toggleLike(likeRequestDto: LikeRequestDto) {
        toggleLikeUseCase.execute(likeRequestDto.blogId, likeRequestDto.userId)
    }

    private fun mapBlogWithAuthor(blog: BlogEntity): BlogResponseDto {
        val user = findUserByIdUseCase.execute(blog.userId)
        val authorResponseDto = AuthorResponseDto.fromEntity(user)
        return BlogResponseDto.fromEntity(blog, authorResponseDto)
    }
}