package de.comsystoreply.gearbox.application.user.adapter.web

import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignInUseCase
import de.comsystoreply.gearbox.application.user.adapter.api.auth.UserSignUpUseCase
import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationResponseDto
import de.comsystoreply.gearbox.domain.user.port.api.UserAlreadyExistsException
import de.comsystoreply.gearbox.domain.user.port.api.UserNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AuthenticationRestApiFacadeTest {

    private lateinit var userSignInUseCase: UserSignInUseCase
    private lateinit var userSignUpUseCase: UserSignUpUseCase
    private lateinit var authenticationRestApiFacade: AuthenticationRestApiFacade

    @BeforeEach
    fun setUp() {
        userSignInUseCase = mockk()
        userSignUpUseCase = mockk()
        authenticationRestApiFacade = AuthenticationRestApiFacade(userSignInUseCase, userSignUpUseCase)
    }

    @Test
    fun `signIn should return AuthenticationResponseDto on valid AuthenticationRequestDto`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val requestDto = AuthenticationRequestDto(email = email, password = password)
        val userEntity = UserEntity("id", email, "testuser", password, null)
        val expectedResponse = AuthenticationResponseDto("", "id", email, "testuser", null)

        every { userSignInUseCase.execute(requestDto) } returns userEntity

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
        val expectedResponse = AuthenticationResponseDto("", "id", email, "testuser", null)

        every { userSignUpUseCase.execute(requestDto) } returns expectedUser

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