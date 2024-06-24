package de.comsystoreply.gearbox.application.adapter.web

import de.comsystoreply.gearbox.application.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.port.web.AuthenticationResponseDto
import de.comsystoreply.gearbox.application.port.web.AuthenticationWebFacade
import de.comsystoreply.gearbox.application.adapter.api.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.adapter.api.auth.UserSignUpUseCase
import org.springframework.stereotype.Service

@Service
class AuthenticationRestApiFacade(
    private val userSignInUseCase: UserSignInUseCase,
    private val userSignUpUseCase: UserSignUpUseCase
) : AuthenticationWebFacade {
    override fun signIn(authenticationRequestDto: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignInUseCase.execute(authenticationRequestDto)
        return AuthenticationResponseDto("", user)
    }

    override fun signUp(authenticationRequestDto: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignUpUseCase.execute(authenticationRequestDto)
        return AuthenticationResponseDto("", user)
    }
}