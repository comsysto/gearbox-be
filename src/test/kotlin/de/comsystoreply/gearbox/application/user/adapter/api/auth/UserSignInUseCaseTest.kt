package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.model.UserEntity
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
        val domainUser = User("id", "test@example.com", "test", "ValidPass123!", null)
        val expectedUser = UserEntity.fromDomain(domainUser)

        every { authenticationApiFacade.signIn(any()) } returns domainUser

        val actualUser = userSignInUseCase.execute(request)

        assertEquals(expectedUser, actualUser)
        verify { authenticationApiFacade.signIn(any()) }
    }

    @Test
    fun `sign in should throw exception on invalid input`() {
        val request = AuthenticationRequestDto(email = "test@example.com", password = "short")

        every {
            authenticationApiFacade.signIn(any())
        } throws PasswordPolicyViolationException("Password must have at least eight characters.")

        val exception = assertThrows<PasswordPolicyViolationException> {
            userSignInUseCase.execute(request)
        }

        assertEquals("Password must have at least eight characters.", exception.message)
    }
}