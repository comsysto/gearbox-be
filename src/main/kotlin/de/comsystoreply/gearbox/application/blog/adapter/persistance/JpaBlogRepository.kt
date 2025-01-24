package de.comsystoreply.gearbox.application.blog.adapter.persistance

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.model.Blog
import de.comsystoreply.gearbox.domain.blog.port.persistance.BlogRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.findByIdOrNull
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Repository
class JpaBlogRepository(
    private val blogRepository: JpaBlogEntityRepository
) : BlogRepository {
    override fun findById(id: String): Blog? {
        return blogRepository
            .findByIdOrNull(id)
            ?.toDomain()
    }

    override fun findTrending(
        startOfWeek: LocalDateTime,
        endOfWeek: LocalDateTime,
        pageable: Pageable
    ): Page<Blog> {
        return blogRepository
            .findTop5TrendingBlogs(startOfWeek, endOfWeek, pageable)
            .map { it.toDomain() }
    }

    override fun findLatest(pageable: Pageable): Page<Blog> {
        return blogRepository
            .findLatestBlogs(pageable)
            .map { it.toDomain() }
    }

    override fun findByAuthor(userId: String, pageable: Pageable): Page<Blog> {
        return blogRepository
            .findAllByUserId(userId, pageable)
            .map { it.toDomain() }
    }

    override fun findLikedBy(userId: String, pageable: Pageable): Page<Blog> {
        return blogRepository
            .findLikedBy(userId, pageable)
            .map { it.toDomain() }
    }

    override fun isBlogLikedBy(blogId: String, userId: String): Boolean {
        return blogRepository.findBlogLikedBy(blogId, userId) != null
    }

    override fun search(query: String, pageable: Pageable): Page<Blog> {
        return blogRepository
            .findAllByTitleContainingIgnoreCase(query, pageable)
            .map { it.toDomain() }
    }

    override fun like(blogId: String, userId: String) = blogRepository.like(blogId, userId)

    override fun removeLike(blogId: String, userId: String) = blogRepository.removeLike(blogId, userId)

    override fun updateLikeCount(blogId: String, likeCount: Int) = blogRepository.updateLikeCount(blogId, likeCount)
}

interface JpaBlogEntityRepository : JpaRepository<BlogEntity, String> {
    @Query(
        """
        select * from blog b
        where b.create_date >= :startOfWeek
        and b.create_date < :endOfWeek
        order by b.number_of_likes desc
        """, nativeQuery = true
    )
    fun findTop5TrendingBlogs(
        @Param("startOfWeek") startOfWeek: LocalDateTime,
        @Param("endOfWeek") endOfWeek: LocalDateTime,
        pageable: Pageable
    ): Page<BlogEntity>

    @Query(
        """
        select * from blog b
        order by b.create_date desc
        """, nativeQuery = true
    )
    fun findLatestBlogs(pageable: Pageable): Page<BlogEntity>

    fun findAllByUserId(userId: String, pageable: Pageable): Page<BlogEntity>

    @Query(
        """
        select b.* from blog b
        inner join blog_likes bl on b.id = bl.blog_id
        where bl.user_id = :userId
        """, nativeQuery = true
    )
    fun findLikedBy(@Param("userId") userId: String, pageable: Pageable): Page<BlogEntity>

    fun findAllByTitleContainingIgnoreCase(query: String, pageable: Pageable): Page<BlogEntity>

    @Query(
        """
        select b.* from blog b
        inner join blog_likes bl on b.id = bl.blog_id
        where b.id = :blogId and bl.user_id = :userId
        """, nativeQuery = true
    )
    fun findBlogLikedBy(@Param("blogId") blogId: String, @Param("userId") userId: String): BlogEntity?

    @Transactional
    @Modifying
    @Query(
        """
        insert into blog_likes (blog_id, user_id) values (:blogId, :userId) 
        """, nativeQuery = true
    )
    fun like(@Param("blogId") blogId: String, @Param("userId") userId: String)

    @Transactional
    @Modifying
    @Query(
        """
        delete from blog_likes bl
        where bl.user_id = :userId and bl.blog_id = :blogId
        """, nativeQuery = true
    )
    fun removeLike(@Param("blogId") blogId: String, @Param("userId") userId: String)

    @Transactional
    @Modifying
    @Query(
        """
        update blog b
        set number_of_likes = :likeCount
        where b.id = :blogId
        """, nativeQuery = true
    )
    fun updateLikeCount(@Param("blogId") blogId: String, @Param("likeCount") likeCount: Int)
}