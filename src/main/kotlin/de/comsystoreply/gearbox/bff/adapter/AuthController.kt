package de.comsystoreply.gearbox.bff.adapter

import de.comsystoreply.gearbox.application.port.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.port.AuthenticationResponseDto
import de.comsystoreply.gearbox.application.port.AuthRestApiFacade
import de.comsystoreply.gearbox.application.adapter.usecase.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.adapter.usecase.auth.UserSignUpUseCase
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userSignInUseCase: UserSignInUseCase,
    private val userSignUpUseCase: UserSignUpUseCase
) : AuthRestApiFacade {
    @PostMapping("/signIn")
    override fun signIn(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto> {
        val user = userSignInUseCase.execute(authenticationRequestDto)
        val response = AuthenticationResponseDto("token", user)
        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/signUp")
    override fun signUp(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<AuthenticationResponseDto> {
        val user = userSignUpUseCase.execute(authenticationRequestDto)
        val response = AuthenticationResponseDto("token", user)
        return ResponseEntity(response, HttpStatus.OK)
    }
}