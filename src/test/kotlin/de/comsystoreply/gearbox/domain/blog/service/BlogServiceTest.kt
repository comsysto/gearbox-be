package de.comsystoreply.gearbox.domain.blog.service

import de.comsystoreply.gearbox.domain.blog.model.Blog
import de.comsystoreply.gearbox.domain.blog.model.BlogCategory
import de.comsystoreply.gearbox.domain.blog.port.persistance.BlogRepository
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class BlogServiceTest {

    private lateinit var blogRepository: BlogRepository
    private lateinit var userRepository: UserRepository
    private lateinit var blogService: BlogService

    private val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss+00")
    private val blog1 = Blog(
        id = "0fcdbb1f-4fdc-4a47-9a18-c69f339b589b",
        title = "The Timeless Charm of Classic Cars",
        content = "Classic cars continue to captivate enthusiasts with their timeless design, craftsmanship, and the nostalgia they evoke. From the curves of a vintage Ferrari to the elegance of a 1960s Mustang, these vehicles are more than just cars; they are pieces of art on wheels.",
        thumbnailImageUrl = "https://www.netcarshow.com/Mercedes-Benz-SL-Class-1971-1280-1a0aa47d17995bf9c4b4182a00672e8544.jpg",
        userId = "dcb9483c-db19-4c03-b9f4-75a7d6e1d282",
        createDate = LocalDateTime.parse("2024-06-29 09:15:00+00", formatter),
        numberOfLikes = 17,
        category = BlogCategory.OLDTIMER
    )
    private val blog2 = Blog(
        id = "8c4a9eeb-77b4-4947-a8d7-1b5b7894b92c",
        title = "Exploring the World of Exotic Supercars",
        content = "Exotic supercars represent the pinnacle of automotive engineering, with their powerful engines, sleek designs, and unparalleled performance. From Lamborghini to Bugatti, these cars are designed to thrill, turning heads wherever they go.",
        thumbnailImageUrl = "https://d1i1eo6qmdfmdv.cloudfront.net/upload/site/pages/newslider_otherpages/slider/EXR_FERRARI_ALL_SUPERCARS_SLIDERS_0004_1.jpg",
        userId = "e23052b6-c083-4796-b3a7-52e737fe2e05",
        createDate = LocalDateTime.parse("2024-07-28 11:45:00+00", formatter),
        numberOfLikes = 31,
        category = BlogCategory.EXOTIC
    )
    private val blog3 = Blog(
        id = "b6507f13-9936-4ef0-93d1-9f73bb5fcedb",
        title = "The Future of Electric Vehicles",
        content = "The automotive industry is rapidly shifting towards electric vehicles (EVs), with advancements in battery technology, charging infrastructure, and autonomous driving. These innovations are not just transforming how we drive, but also how we think about the environment and sustainability.",
        thumbnailImageUrl = "https://www.netcarshow.com/MG-ZS_Hybrid-2025-1280-8f1072f01e9c7f8cfaeab36bc03ccb8fa4.jpg",
        userId = "3f903ecb-8087-4cdf-ad46-953f4a000a17",
        createDate = LocalDateTime.parse("2025-01-21 11:45:00+00", formatter),
        numberOfLikes = 69,
        category = BlogCategory.TECHNOLOGY
    )
    private val blog4 = Blog(
        id = "fa2a3b1e-4cb5-4c59-bd28-ef214ae2b3a5",
        title = "Breaking: Major Automaker Announces New Hydrogen-Powered Vehicle",
        content = "In a surprising move, a leading automaker has announced the launch of a new hydrogen-powered vehicle, aiming to rival the growing electric vehicle market. This marks a significant development in the pursuit of alternative fuels and could reshape the future of automotive energy.",
        thumbnailImageUrl = "https://i.ytimg.com/vi/Ppuvvr6rcb0/maxresdefault.jpg",
        userId = "3f903ecb-8087-4cdf-ad46-953f4a000a17",
        createDate = LocalDateTime.parse("2025-01-20 13:26:00+00", formatter),
        numberOfLikes = 13,
        category = BlogCategory.HOT_NEWS
    )

    @BeforeEach
    fun setUp() {
        blogRepository = mockk()
        userRepository = mockk()
        blogService = BlogService(blogRepository, userRepository)
    }

    @Test
    fun `findTrending should find the most liked blogs in the current week and return as list`() {
        val startOfWeek = LocalDateTime.now()
            .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
            .with(LocalTime.MIN)
        val endOfWeek = startOfWeek.plusWeeks(1).with(LocalTime.MAX)

        val pageable = PageRequest.of(0, 10)

        val trendingBlogs = listOf(blog3, blog4)
        val pageableTrendingList: Page<Blog> = PageImpl(trendingBlogs, pageable, trendingBlogs.size.toLong())

        every { blogRepository.findTrending(startOfWeek, endOfWeek, pageable) } returns pageableTrendingList

        val actualBlogs = blogService.findTrending(pageable)

        assertEquals(pageableTrendingList, actualBlogs)
        assertEquals(actualBlogs.first().title, "The Future of Electric Vehicles")
        verify { blogRepository.findTrending(startOfWeek, endOfWeek, pageable) }
    }
}