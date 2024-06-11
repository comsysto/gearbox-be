package de.comsystoreply.gearbox.bff.usecase.auth

import de.comsystoreply.gearbox.bff.dto.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationService
import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationSignUpCommand
import de.comsystoreply.gearbox.domain.user.port.application.UserAlreadyExistsException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class UserSignUpUseCaseTest {

    private lateinit var userSignUpUseCase: UserSignUpUseCase
    private lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setUp() {
        authenticationService = mockk()
        userSignUpUseCase = UserSignUpUseCase(authenticationService)
    }

    @Test
    fun `use case should return new User on valid AuthenticationRequestDto`() {
        val request = AuthenticationRequestDto(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
        )
        val command = AuthenticationSignUpCommand(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )
        val expectedUser =
            User(
                id = "id",
                email = "test@example.com",
                username = "testuser",
                password = "ValidPass123!",
                profileImageUrl = null
            )

        every { authenticationService.signUp(command) } returns expectedUser

        val actualUser = userSignUpUseCase.execute(request)

        assertEquals(expectedUser, actualUser)
        verify { authenticationService.signUp(command) }
    }

    @Test
    fun `use case should throw UserAlreadyExistsException on existing command`() {
        val request = AuthenticationRequestDto(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
        )
        val command = AuthenticationSignUpCommand(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )

        every { authenticationService.signUp(command) } throws UserAlreadyExistsException("User already exists.")

        val exception = assertThrows<UserAlreadyExistsException> { userSignUpUseCase.execute(request) }

        assertEquals(exception.message, "User already exists.")
        verify { authenticationService.signUp(command) }
    }
}