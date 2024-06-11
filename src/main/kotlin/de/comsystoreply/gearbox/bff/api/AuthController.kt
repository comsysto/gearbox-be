package de.comsystoreply.gearbox.bff.api

import de.comsystoreply.gearbox.bff.dto.AuthenticationRequestDto
import de.comsystoreply.gearbox.bff.usecase.auth.UserSignInUseCase
import de.comsystoreply.gearbox.bff.usecase.auth.UserSignUpUseCase
import de.comsystoreply.gearbox.domain.user.model.User
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
) {
    @PostMapping("/signIn")
    fun signIn(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<User> {
        val user = userSignInUseCase.execute(authenticationRequestDto)
        return ResponseEntity(user, HttpStatus.OK)
    }

    @PostMapping("/signUp")
    fun signUp(@RequestBody authenticationRequestDto: AuthenticationRequestDto): ResponseEntity<User> {
        val user = userSignUpUseCase.execute(authenticationRequestDto)
        return ResponseEntity(user, HttpStatus.OK)
    }
}