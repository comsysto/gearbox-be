package de.comsystoreply.gearbox.bff.adapter.web

import com.fasterxml.jackson.databind.ObjectMapper
import de.comsystoreply.gearbox.application.blog.port.web.AuthorResponseDto
import de.comsystoreply.gearbox.application.blog.port.web.BlogResponseDto
import de.comsystoreply.gearbox.application.blog.port.web.BlogWebFacade
import de.comsystoreply.gearbox.application.blog.port.web.LikeRequestDto
import de.comsystoreply.gearbox.bff.config.GlobalExceptionHandler
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.Import
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import java.time.LocalDateTime
import kotlin.test.assertEquals

@Import(GlobalExceptionHandler::class)
class BlogControllerTest {

    private val webFacade: BlogWebFacade = mockk()
    private val controller = BlogController(webFacade)
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    private val objectMapper = ObjectMapper()

    @Test
    fun `findTrending should return page of blog posts`() {
        val pageRequest = PageRequest.of(0, 10)
        val author = AuthorResponseDto("id", "username", "url")
        val blogPost = BlogResponseDto("id", "title", "body", "url", LocalDateTime.now(), 0,0, "", author)
        val expectedPage: Page<BlogResponseDto> = PageImpl(listOf(blogPost), pageRequest, 1)

        every { webFacade.findTrending(pageRequest) } returns expectedPage

        mockMvc.perform(get("/api/blog/trending/0/10"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value("id"))
            .andExpect(jsonPath("$.content[0].title").value("title"))

        verify(exactly = 1) { webFacade.findTrending(pageRequest) }
    }

    @Test
    fun `findTrending should handle exception when facade throws error`() {
        val pageRequest = PageRequest.of(0, 10)
        lateinit var result: RuntimeException

        every { webFacade.findTrending(pageRequest) } throws RuntimeException("Database connection failed")

        try {
            controller.findTrending(pageRequest.pageNumber, pageRequest.pageSize)
        } catch (e: RuntimeException) {
            result = e
        }

        // When & Then
        assertEquals(result.message, "Database connection failed")

        verify(exactly = 1) { webFacade.findTrending(pageRequest) }
    }

    @Test
    fun `findLatest should return page of latest blog posts`() {
        val pageRequest = PageRequest.of(0, 10)
        val author = AuthorResponseDto("id", "username", "url")
        val blogPost = BlogResponseDto("id", "title", "body", "url", LocalDateTime.now(), 0, 0,"", author)
        val expectedPage: Page<BlogResponseDto> = PageImpl(listOf(blogPost), pageRequest, 1)

        every { webFacade.findLatest(pageRequest) } returns expectedPage

        mockMvc.perform(get("/api/blog/latest/0/10"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value("id"))
            .andExpect(jsonPath("$.content[0].title").value("title"))

        verify(exactly = 1) { webFacade.findLatest(pageRequest) }
    }

    @Test
    fun `findByAuthorId should return page of blog posts by author`() {
        val userId = "id"
        val pageRequest = PageRequest.of(0, 10)
        val author = AuthorResponseDto(userId, "username", "url")
        val blogPost = BlogResponseDto("id", "title", "body", "url", LocalDateTime.now(), 0, 0,"", author)
        val expectedPage: Page<BlogResponseDto> = PageImpl(listOf(blogPost), pageRequest, 1)

        every { webFacade.findByAuthor(userId, pageRequest) } returns expectedPage

        mockMvc.perform(get("/api/blog/byAuthor/$userId/0/10"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value("id"))
            .andExpect(jsonPath("$.content[0].author.id").value(userId))

        verify(exactly = 1) { webFacade.findByAuthor(userId, pageRequest) }
    }

    @Test
    fun `findLikedBy should return page of blog posts liked by user`() {
        val userId = "id"
        val pageRequest = PageRequest.of(0, 10)
        val author = AuthorResponseDto(userId, "username", "url")
        val blogPost = BlogResponseDto("id", "title", "body", "url", LocalDateTime.now(), 0, 0,"", author)
        val expectedPage: Page<BlogResponseDto> = PageImpl(listOf(blogPost), pageRequest, 1)

        every { webFacade.findLikedBy(userId, pageRequest) } returns expectedPage

        mockMvc.perform(get("/api/blog/likedBy/$userId/0/10"))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value("id"))

        verify(exactly = 1) { webFacade.findLikedBy(userId, pageRequest) }
    }

    @Test
    fun `search should return page of blog posts matching query`() {
        val query = "search term"
        val pageRequest = PageRequest.of(0, 10)
        val author = AuthorResponseDto("id", "username", "url")
        val blogPost = BlogResponseDto("id", "title", "body", "url", LocalDateTime.now(), 0, 0,"", author)
        val expectedPage: Page<BlogResponseDto> = PageImpl(listOf(blogPost), pageRequest, 1)

        every { webFacade.search(any(), any()) } returns expectedPage

        mockMvc.perform(
            post("/api/blog/search/0/10")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(query))
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.content[0].id").value("id"))

        verify(exactly = 1) { webFacade.search(any(), any()) }
    }

    @Test
    fun `toggleLike should process like request and return OK`() {
        val likeRequest = LikeRequestDto("userId", "blogId")

        every { webFacade.toggleLike(likeRequest) } returns Unit

        mockMvc.perform(
            post("/api/blog/toggleLike")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(likeRequest))
        )
            .andExpect(status().isOk)

        verify(exactly = 1) { webFacade.toggleLike(likeRequest) }
    }
}