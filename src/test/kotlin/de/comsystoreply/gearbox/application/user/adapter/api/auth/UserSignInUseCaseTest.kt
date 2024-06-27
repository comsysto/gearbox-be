package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.AuthenticationApiFacade
import de.comsystoreply.gearbox.domain.user.port.api.PasswordPolicyViolationException
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserSignInUseCaseTest {

    private lateinit var authenticationApiFacade: AuthenticationApiFacade
    private lateinit var userSignInUseCase: UserSignInUseCase

    @BeforeEach
    fun setUp() {
        authenticationApiFacade = mockk()
        userSignInUseCase = UserSignInUseCase(authenticationApiFacade)
    }

    @Test
    fun `sign in should return user on valid input`() {
        val request = AuthenticationRequestDto(email = "test@example.com", password = "ValidPass123!")
        val expectedUser = User("id", "test@example.com", "test", "ValidPass123!", null)
        every { authenticationApiFacade.signIn(any(), any()) } returns expectedUser

        val actualUser = userSignInUseCase.execute(request)

        assertEquals(expectedUser, actualUser)
        verify { authenticationApiFacade.signIn(expectedUser.email, expectedUser.password) }
    }

    @Test
    fun `sign in should throw exception on invalid input`() {
        val request = AuthenticationRequestDto(email = "test@example.com", password = "short")

        every {
            authenticationApiFacade.signIn(any(), any())
        } throws PasswordPolicyViolationException("Password must have at least eight characters.")

        val exception = assertThrows<PasswordPolicyViolationException> {
            userSignInUseCase.execute(request)
        }

        assertEquals("Password must have at least eight characters.", exception.message)
    }
}