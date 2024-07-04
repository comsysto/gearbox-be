package de.comsystoreply.gearbox.application.user.adapter.api.auth

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.application.user.port.web.AuthenticationRequestDto
import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.PasswordPolicyViolationException
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals

class UserSignUpUseCaseTest {

    private lateinit var userApiFacade: UserApiFacade
    private lateinit var userSignUpUseCase: UserSignUpUseCase

    @BeforeEach
    fun setUp() {
        userApiFacade = mockk()
        userSignUpUseCase = UserSignUpUseCase(userApiFacade)
    }

    @Test
    fun `signUp should return new user on valid input`() {
        val request = AuthenticationRequestDto(
            email = "test@example.com",
            username = "test",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!"
        )
        val domainUser = User("id", "test@example.com", "test", "ValidPass123!", null)
        val expectedUser = UserEntity.fromDomain(domainUser)

        every { userApiFacade.signUp(any()) } returns domainUser

        val actualUser = userSignUpUseCase.execute(request)

        assertEquals(expectedUser, actualUser)
        verify { userApiFacade.signUp(any()) }
    }

    @Test
    fun `signIn throw exception on invalid input`() {
        val request = AuthenticationRequestDto(
            email = "test@example.com",
            username = "test",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!"
        )

        every {
            userApiFacade.signUp(any())
        } throws PasswordPolicyViolationException("Password must have at least eight characters.")

        val exception = assertThrows<PasswordPolicyViolationException> {
            userSignUpUseCase.execute(request)
        }

        Assertions.assertEquals("Password must have at least eight characters.", exception.message)
    }
}