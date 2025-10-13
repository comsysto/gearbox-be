package de.comsystoreply.gearbox.domain.blog.model

data class Comment(
    val id: String,
    val blogId: String,
    val userId: String,
    val content: String,
)