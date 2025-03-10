package de.comsystoreply.gearbox.application.user.facade.web

import de.comsystoreply.gearbox.application.user.usecase.GenerateTokenUseCase
import de.comsystoreply.gearbox.application.user.usecase.GetTokenOwnerUseCase
import de.comsystoreply.gearbox.application.user.usecase.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.usecase.UserSignUpUseCase
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