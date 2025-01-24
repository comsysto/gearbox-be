package de.comsystoreply.gearbox.application.blog.model

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.blog.model.Blog
import de.comsystoreply.gearbox.domain.blog.model.BlogCategory
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "blog")
data class BlogEntity(
    @Id
    val id: String,
    val title: String,

    @Column(columnDefinition = "TEXT")
    val content: String,

    val thumbnailImageUrl: String,
    val userId: String,
    val createDate: LocalDateTime,
    var numberOfLikes: Int,

    @Enumerated(EnumType.STRING)
    val category: BlogCategoryEntity,

    @ManyToMany(mappedBy = "likedBlogs")
    val userLikes: MutableSet<UserEntity> = mutableSetOf()
) {
    fun toDomain(): Blog {
        return Blog(
            id = id,
            title = title,
            content = content,
            thumbnailImageUrl = thumbnailImageUrl,
            userId = userId,
            createDate = createDate,
            numberOfLikes = numberOfLikes,
            category = BlogCategory.valueOf(category.name),
        )
    }

    companion object {
        fun fromDomain(blog: Blog): BlogEntity {
            return BlogEntity(
                id = blog.id,
                title = blog.title,
                content = blog.content,
                thumbnailImageUrl = blog.thumbnailImageUrl,
                userId = blog.userId,
                createDate = blog.createDate,
                numberOfLikes = blog.numberOfLikes,
                category = BlogCategoryEntity.valueOf(blog.category.name),
            )
        }
    }
}

enum class BlogCategoryEntity {
    TECHNOLOGY,
    HOT_NEWS,
    CONCEPTS,
    ELECTRIC_CARS,
    EXOTIC,
    OLDTIMER
}