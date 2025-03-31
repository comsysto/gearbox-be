package de.comsystoreply.gearbox.bff.adapter.web

import de.comsystoreply.gearbox.application.blog.port.web.BlogResponseDto
import de.comsystoreply.gearbox.application.blog.port.web.BlogWebFacade
import de.comsystoreply.gearbox.application.blog.port.web.LikeRequestDto
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blog")
class BlogController(
    private val webFacade: BlogWebFacade
) {
    @GetMapping("/trending/{page}/{size}")
    fun findTrending(@PathVariable page: Int, @PathVariable size: Int): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findTrending(pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/latest/{page}/{size}")
    fun findLatest(@PathVariable page: Int, @PathVariable size: Int): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findLatest(pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/byAuthor/{userId}/{page}/{size}")
    fun findByAuthorId(
        @PathVariable userId: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findByAuthor(userId, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/likedBy/{userId}/{page}/{size}")
    fun findLikedBy(
        @PathVariable userId: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findLikedBy(userId, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/search/{page}/{size}")
    fun search(
        @RequestBody query: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.search(query, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/toggleLike")
    fun toggleLike(@RequestBody likeRequestDto: LikeRequestDto): ResponseEntity<Unit> {
        webFacade.toggleLike(likeRequestDto)
        return ResponseEntity(HttpStatus.OK)
    }
}