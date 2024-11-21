package de.comsystoreply.gearbox.application.user.adapter.web

import de.comsystoreply.gearbox.application.user.adapter.api.auth.GenerateTokenUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.GetTokenOwnerUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignUpUseCase
import de.comsystoreply.gearbox.application.user.port.web.*
import org.springframework.stereotype.Service

@Service
class AuthenticationRestApiFacade(
    private val userSignInUseCase: UserSignInUseCase,
    private val userSignUpUseCase: UserSignUpUseCase,
    private val generateTokenUseCase: GenerateTokenUseCase,
    private val getTokenOwnerUseCase: GetTokenOwnerUseCase
) : AuthenticationWebFacade {

    override fun signIn(request: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignInUseCase.execute(request)
        return generateTokenUseCase.execute(user)
    }

    override fun signUp(request: AuthenticationRequestDto): AuthenticationResponseDto {
        val user = userSignUpUseCase.execute(request)
        return generateTokenUseCase.execute(user)
    }

    override fun refreshToken(request: RefreshTokenRequestDto): AuthenticationResponseDto {
        val user = getTokenOwnerUseCase.execute(request)
        return generateTokenUseCase.execute(user)
    }
}