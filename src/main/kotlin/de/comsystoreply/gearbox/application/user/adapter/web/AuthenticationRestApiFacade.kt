package de.comsystoreply.gearbox.application.user.adapter.web

import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationResponseDto
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationWebFacade
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignUpUseCase
import org.springframework.stereotype.Service

@Service
class AuthenticationRestApiFacade(
    private val userSignInUseCase: UserSignInUseCase,
    private val userSignUpUseCase: UserSignUpUseCase
) : AuthenticationWebFacade {
    override fun signIn(authenticationRequestDto: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignInUseCase.execute(authenticationRequestDto)
        return AuthenticationResponseDto("", user.id, user.email, user.username, user.profileImageUrl)
    }

    override fun signUp(authenticationRequestDto: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignUpUseCase.execute(authenticationRequestDto)
        return AuthenticationResponseDto("", user.id, user.email, user.username, user.profileImageUrl)
    }
}