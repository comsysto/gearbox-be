package de.comsystoreply.gearbox.bff.adapter.web

import de.comsystoreply.gearbox.application.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.port.web.AuthenticationResponseDto
import de.comsystoreply.gearbox.application.port.web.AuthenticationWebFacade
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val webFacade: AuthenticationWebFacade,
) {
    @PostMapping("/signIn")
    fun signIn(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto> {
        val response = webFacade.signIn(authenticationRequestDto)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto> {
        val response = webFacade.signUp(authenticationRequestDto)
        return ResponseEntity(response, HttpStatus.OK)
    }
}