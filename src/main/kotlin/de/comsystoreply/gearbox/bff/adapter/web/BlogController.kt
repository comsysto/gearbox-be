package de.comsystoreply.gearbox.bff.adapter.web

import de.comsystoreply.gearbox.application.blog.port.web.*
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/blog")
final class BlogController(
    private val webFacade: BlogWebFacade
) {
    @GetMapping("/trending/{page}/{size}")
    final fun findTrending(@PathVariable page: Int, @PathVariable size: Int): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findTrending(pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/latest/{page}/{size}")
    final fun findLatest(@PathVariable page: Int, @PathVariable size: Int): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findLatest(pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/byAuthor/{userId}/{page}/{size}")
    final fun findByAuthorId(
        @PathVariable userId: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findByAuthor(userId, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/likedBy/{userId}/{page}/{size}")
    final fun findLikedBy(
        @PathVariable userId: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findLikedBy(userId, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/search/{page}/{size}")
    final fun search(
        @RequestBody query: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<BlogResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.search(query, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/toggleLike")
    final fun toggleLike(@RequestBody likeRequestDto: LikeRequestDto): ResponseEntity<Unit> {
        webFacade.toggleLike(likeRequestDto)
        return ResponseEntity(HttpStatus.OK)
    }

    @PostMapping("/comment")
    final fun makeComment(@RequestBody commentRequestDto: CommentRequestDto): ResponseEntity<Page<CommentResponseDto>> {
        val response = webFacade.makeComment(commentRequestDto)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/comment/{id}/{page}/{size}")
    final fun findCommentsForBlog(
        @PathVariable id: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<CommentResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = webFacade.findCommentsForBlog(id, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }
}