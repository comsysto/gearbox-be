package de.comsystoreply.gearbox.bff.usecase.auth

import de.comsystoreply.gearbox.bff.dto.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationService
import de.comsystoreply.gearbox.domain.user.port.application.UserNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class UserSignInUseCaseTest {

    private lateinit var useCase: UserSignInUseCase
    private lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setUp() {
        authenticationService = mockk()
        useCase = UserSignInUseCase(authenticationService)
    }

    @Test
    fun `use case should return valid user on valid AuthenticationRequestDto`() {
        val request = AuthenticationRequestDto(email = "test@test.com", password = "test")
        val expectedUser =
            User(
                id = "id",
                email = request.email,
                username = "testuser",
                password = request.password,
                profileImageUrl = null
            )

        every { authenticationService.signIn(request.email, request.password) } returns expectedUser

        val actualUser = useCase.execute(request)

        assertEquals(expectedUser, actualUser)
        verify { authenticationService.signIn(request.email, request.password) }
    }

    @Test
    fun `use case should throw UserNotFoundException invalid AuthenticationRequestDto`() {
        val request = AuthenticationRequestDto(email = "test@test.com", password = "test")

        every {
            authenticationService.signIn(
                request.email,
                request.password
            )
        } throws UserNotFoundException("User is not found")

        val exception = assertThrows<UserNotFoundException> {
            useCase.execute(request)
        }

        assertEquals("User is not found", exception.message)
        verify { authenticationService.signIn(request.email, request.password) }
    }
}