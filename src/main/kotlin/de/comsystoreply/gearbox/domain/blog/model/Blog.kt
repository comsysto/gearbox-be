package de.comsystoreply.gearbox.domain.blog.model

import java.time.LocalDateTime

data class Blog(
    val id: String,
    val title: String,
    val content: String,
    val thumbnailImageUrl: String,
    val userId: String,
    val createDate: LocalDateTime,
    var numberOfLikes: Int,
    val category: BlogCategory
)
