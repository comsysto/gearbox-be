package de.comsystoreply.gearbox.application.blog.adapter.persistance

import de.comsystoreply.gearbox.application.blog.model.BlogEntity
import de.comsystoreply.gearbox.domain.blog.model.Blog
import de.comsystoreply.gearbox.domain.blog.port.persistance.BlogRepository
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

    override fun findTrending(startOfWeek: LocalDateTime, endOfWeek: LocalDateTime): List<Blog> {
        return blogRepository
            .findTop5TrendingBlogs(startOfWeek, endOfWeek)
            .map { it.toDomain() }
    }

    override fun findLatest(): List<Blog> {
        return blogRepository
            .findLatestBlogs()
            .map { it.toDomain() }
    }

    override fun findByAuthor(userId: String): List<Blog> {
        return blogRepository
            .findAllByUserId(userId)
            .map { it.toDomain() }
    }

    override fun findLikedBy(userId: String): List<Blog> {
        return blogRepository
            .findLikedBy(userId)
            .map { it.toDomain() }
    }

    override fun isBlogLikedBy(blogId: String, userId: String): Boolean {
        return blogRepository.findBlogLikedBy(blogId, userId) != null
    }

    override fun search(query: String): List<Blog> {
        return blogRepository
            .findAllByTitleContainingIgnoreCase(query)
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
        limit 5
        """, nativeQuery = true
    )
    fun findTop5TrendingBlogs(
        @Param("startOfWeek") startOfWeek: LocalDateTime,
        @Param("endOfWeek") endOfWeek: LocalDateTime
    ): List<BlogEntity>

    @Query(
        """
        select b from BlogEntity b
        order by b.createDate desc
        limit 20
        """
    )
    fun findLatestBlogs(): List<BlogEntity>

    fun findAllByUserId(userId: String): List<BlogEntity>

    @Query(
        """
        select b.* from blog b
        inner join blog_likes bl on b.id = bl.blog_id
        where bl.user_id = :userId
        """, nativeQuery = true
    )
    fun findLikedBy(@Param("userId") userId: String): List<BlogEntity>

    fun findAllByTitleContainingIgnoreCase(query: String): List<BlogEntity>

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