package de.comsystoreply.gearbox.bff.adapter.web

import de.comsystoreply.gearbox.application.user.port.web.UserResponseDto
import de.comsystoreply.gearbox.application.user.port.web.UserWebFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
class UserController(
    private val userWebFacade: UserWebFacade
) {
    @PostMapping("/search/{page}/{size}")
    fun search(
        @RequestBody query: String,
        @PathVariable page: Int,
        @PathVariable size: Int
    ): ResponseEntity<Page<UserResponseDto>> {
        val pageRequest = PageRequest.of(page, size)
        val response = userWebFacade.search(query, pageRequest)
        return ResponseEntity(response, HttpStatus.OK)
    }
}