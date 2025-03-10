package de.comsystoreply.gearbox.application.user.facade

import de.comsystoreply.gearbox.application.security.config.JwtProperties
import de.comsystoreply.gearbox.application.user.facade.web.AuthenticationRestApiFacade
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationResponseDto
import de.comsystoreply.gearbox.application.user.usecase.GenerateTokenUseCase
import de.comsystoreply.gearbox.application.user.usecase.GetTokenOwnerUseCase
import de.comsystoreply.gearbox.application.user.usecase.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.usecase.UserSignUpUseCase
import de.comsystoreply.gearbox.domain.user.port.api.UserAlreadyExistsException
import de.comsystoreply.gearbox.domain.user.port.api.UserNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.context.properties.EnableConfigurationProperties

@EnableConfigurationProperties(JwtProperties::class)
class AuthenticationRestApiFacadeTest {

    private lateinit var userSignInUseCase: UserSignInUseCase
    private lateinit var userSignUpUseCase: UserSignUpUseCase
    private lateinit var generateTokenUseCase: GenerateTokenUseCase
    private lateinit var getTokenOwnerUseCase: GetTokenOwnerUseCase
    private lateinit var authenticationRestApiFacade: AuthenticationRestApiFacade



    @BeforeEach
    fun setUp() {
        userSignInUseCase = mockk()
        userSignUpUseCase = mockk()
        generateTokenUseCase = mockk()
        getTokenOwnerUseCase = mockk()
        authenticationRestApiFacade = AuthenticationRestApiFacade(
            userSignInUseCase,
            userSignUpUseCase,
            generateTokenUseCase,
            getTokenOwnerUseCase
        )
    }

    @Test
    fun `signIn should return AuthenticationResponseDto on valid AuthenticationRequestDto`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val requestDto = AuthenticationRequestDto(email = email, password = password)
        val userEntity = UserEntity("id", email, "testuser", password, null)
        val expectedResponse = AuthenticationResponseDto("token", "token", "id", email, "testuser", null)

        every { userSignInUseCase.execute(requestDto) } returns userEntity
        every { generateTokenUseCase.execute(userEntity) } returns expectedResponse

        val actualResponse = authenticationRestApiFacade.signIn(requestDto)

        assertEquals(expectedResponse, actualResponse)
        verify { userSignInUseCase.execute(requestDto) }
    }

    @Test
    fun `signIn should throw UserNotFoundException when AuthenticationRequestDto is invalid`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val requestDto = AuthenticationRequestDto(email = email, password = password)

        every { userSignInUseCase.execute(requestDto) } throws UserNotFoundException("User is not found.")

        val exception = assertThrows<UserNotFoundException> { authenticationRestApiFacade.signIn(requestDto) }

        assertEquals(exception.message, "User is not found.")
        verify { userSignInUseCase.execute(requestDto) }
    }

    @Test
    fun `signUp should return AuthenticationResponseDto on valid AuthenticationRequestDto`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val requestDto = AuthenticationRequestDto(email = email, password = password)
        val expectedUser = UserEntity("id", email, "testuser", password, null)
        val expectedResponse = AuthenticationResponseDto("token", "token", "id", email, "testuser", null)

        every { userSignUpUseCase.execute(requestDto) } returns expectedUser
        every { generateTokenUseCase.execute(expectedUser) } returns expectedResponse

        val actualResponse = authenticationRestApiFacade.signUp(requestDto)

        assertEquals(expectedResponse, actualResponse)
        verify { userSignUpUseCase.execute(requestDto) }
    }

    @Test
    fun `signUp should throw UserAlreadyExistsException when user already exists`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val requestDto = AuthenticationRequestDto(email = email, password = password)

        every { userSignUpUseCase.execute(requestDto) } throws UserAlreadyExistsException("User already exists.")

        val exception = assertThrows<UserAlreadyExistsException> { authenticationRestApiFacade.signUp(requestDto) }

        assertEquals(exception.message, "User already exists.")
        verify { userSignUpUseCase.execute(requestDto) }
    }
}