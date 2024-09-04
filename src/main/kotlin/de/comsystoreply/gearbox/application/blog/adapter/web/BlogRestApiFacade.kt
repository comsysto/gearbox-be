package de.comsystoreply.gearbox.application.blog.adapter.web

import de.comsystoreply.gearbox.application.blog.adapter.api.*
import de.comsystoreply.gearbox.application.blog.port.web.BlogResponseDto
import de.comsystoreply.gearbox.application.blog.port.web.BlogWebFacade
import de.comsystoreply.gearbox.application.blog.port.web.LikeRequestDto
import org.springframework.stereotype.Service

@Service
class BlogRestApiFacade(
    private val findTrendingBlogsUseCase: FindTrendingBlogsUseCase,
    private val findLatestBlogsUseCase: FindLatestBlogsUseCase,
    private val findBlogsByAuthorUseCase: FindBlogsByAuthorUseCase,
    private val findBlogsLikedByUserUseCase: FindLikedByUserUseCase,
    private val searchBlogUseCase: SearchBlogsUseCase,
    private val toggleLikeUseCase: ToggleLikeUseCase,
) : BlogWebFacade {
    override fun findTrending(): List<BlogResponseDto> {
        return findTrendingBlogsUseCase.execute().map { BlogResponseDto.fromEntity(it) }
    }

    override fun findLatest(): List<BlogResponseDto> {
        return findLatestBlogsUseCase.execute().map { BlogResponseDto.fromEntity(it) }
    }

    override fun findByAuthor(userId: String): List<BlogResponseDto> {
        return findBlogsByAuthorUseCase.execute(userId).map { BlogResponseDto.fromEntity(it) }
    }

    override fun findLikedBy(userId: String): List<BlogResponseDto> {
        return findBlogsLikedByUserUseCase.execute(userId).map { BlogResponseDto.fromEntity(it) }
    }

    override fun search(query: String): List<BlogResponseDto> {
        return searchBlogUseCase.execute(query).map { BlogResponseDto.fromEntity(it) }
    }

    override fun toggleLike(likeRequestDto: LikeRequestDto) {
        toggleLikeUseCase.execute(likeRequestDto.blogId, likeRequestDto.userId)
    }

}