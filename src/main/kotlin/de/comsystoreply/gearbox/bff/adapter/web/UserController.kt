package de.comsystoreply.gearbox.bff.adapter.web

import de.comsystoreply.gearbox.application.user.port.web.UserResponseDto
import de.comsystoreply.gearbox.application.user.port.web.UserWebFacade
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

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

    @GetMapping("/{id}")
    fun getById(@PathVariable id: String): ResponseEntity<UserResponseDto> {
        val result = userWebFacade.getProfileData(id)
        return ResponseEntity(result, HttpStatus.OK)
    }

    @PostMapping("/profile/uploadImage", consumes = [MediaType.MULTIPART_FORM_DATA_VALUE])
    fun uploadProfileImage(
        @RequestPart("userId") actorUserId: String,
        @RequestPart("image") file: MultipartFile
    ): ResponseEntity<UserResponseDto> {
        val response = userWebFacade.uploadProfileImage(actorUserId, file)
        return ResponseEntity(response, HttpStatus.OK)
    }
}