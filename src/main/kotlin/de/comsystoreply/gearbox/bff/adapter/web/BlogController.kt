package de.comsystoreply.gearbox.bff.adapter.web

import de.comsystoreply.gearbox.application.blog.port.web.BlogResponseDto
import de.comsystoreply.gearbox.application.blog.port.web.BlogWebFacade
import de.comsystoreply.gearbox.application.blog.port.web.LikeRequestDto
import de.comsystoreply.gearbox.application.blog.port.web.SearchQueryDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blog")
class BlogController(
    private val webFacade: BlogWebFacade
) {
    @GetMapping("/trending")
    fun findTrending(): ResponseEntity<List<BlogResponseDto>> {
        val response = webFacade.findTrending()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/latest")
    fun findLatest(): ResponseEntity<List<BlogResponseDto>> {
        val response = webFacade.findLatest()
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/byAuthor/{userId}")
    fun findByAuthorId(@PathVariable userId: String): ResponseEntity<List<BlogResponseDto>> {
        val response = webFacade.findByAuthor(userId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/likedBy/{userId}")
    fun findLikedBy(@PathVariable userId: String): ResponseEntity<List<BlogResponseDto>> {
        val response = webFacade.findLikedBy(userId)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/search")
    fun search(@RequestBody dto: SearchQueryDto): ResponseEntity<List<BlogResponseDto>> {
        val response = webFacade.search(dto.query)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/toggleLike")
    fun toggleLike(@RequestBody likeRequestDto: LikeRequestDto): ResponseEntity<Unit> {
        webFacade.toggleLike(likeRequestDto)
        return ResponseEntity(HttpStatus.OK)
    }
}