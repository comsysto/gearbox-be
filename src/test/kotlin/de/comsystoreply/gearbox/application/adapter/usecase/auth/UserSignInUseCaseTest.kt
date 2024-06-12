package de.comsystoreply.gearbox.application.adapter.usecase.auth

import de.comsystoreply.gearbox.application.port.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationApiFacade
import de.comsystoreply.gearbox.domain.user.port.api.UserNotFoundException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class UserSignInUseCaseTest {

    private lateinit var useCase: UserSignInUseCase
    private lateinit var authenticationApiFacade: AuthenticationApiFacade

    @BeforeEach
    fun setUp() {
        authenticationApiFacade = mockk()
        useCase = UserSignInUseCase(authenticationApiFacade)
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

        every { authenticationApiFacade.signIn(request.email, request.password) } returns expectedUser

        val actualUser = useCase.execute(request)

        assertEquals(expectedUser, actualUser)
        verify { authenticationApiFacade.signIn(request.email, request.password) }
    }

    @Test
    fun `use case should throw UserNotFoundException invalid AuthenticationRequestDto`() {
        val request = AuthenticationRequestDto(email = "test@test.com", password = "test")

        every {
            authenticationApiFacade.signIn(
                request.email,
                request.password
            )
        } throws UserNotFoundException("User is not found")

        val exception = assertThrows<UserNotFoundException> {
            useCase.execute(request)
        }

        assertEquals("User is not found", exception.message)
        verify { authenticationApiFacade.signIn(request.email, request.password) }
    }
}