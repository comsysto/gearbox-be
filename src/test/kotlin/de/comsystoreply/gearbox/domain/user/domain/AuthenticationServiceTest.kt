package de.comsystoreply.gearbox.domain.user.domain

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.*
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import io.mockk.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class AuthenticationServiceTest {

    private lateinit var userRepository: UserRepository
    private lateinit var authenticationService: AuthenticationService

    @BeforeEach
    fun setUp() {
        userRepository = mockk()
        authenticationService = AuthenticationService(userRepository)
    }

    @Test
    fun `signIn should return user when credentials are valid`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val expectedUser =
            User(id = "id", email = email, username = "testuser", password = password, profileImageUrl = null)

        every { userRepository.findByEmailAndPassword(email, password) } returns expectedUser

        val actualUser = authenticationService.signIn(email, password)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `signIn should throw UserNotFoundException when user is not found`() {
        val email = "test@example.com"
        val password = "ValidPass123!"

        every { userRepository.findByEmailAndPassword(email, password) } returns null

        val exception = assertThrows<UserNotFoundException> {
            authenticationService.signIn(email, password)
        }

        assertEquals("User is not found", exception.message)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `signUp should return user when credentials are valid`() {
        val command = AuthenticationSignUpCommand(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )
        val expectedUser = User(
            id = "id",
            email = command.email,
            username = command.username,
            password = command.password,
            profileImageUrl = command.profileImageUrl
        )

        every { userRepository.findByEmailAndPassword(any(), any()) } returns null
        every { userRepository.create(any()) } returns expectedUser

        val actualUser = authenticationService.signUp(command)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.create(any()) }
    }

    @Test
    fun `signUp should throw UserAlreadyExistsException when user exists`() {
        val command = AuthenticationSignUpCommand(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )
        val existingUser = User(
            id = "id",
            email = command.email,
            username = command.username,
            password = command.password,
            profileImageUrl = command.profileImageUrl
        )

        every { userRepository.findByEmailAndPassword(any(), any()) } returns existingUser
        val exception = assertThrows<UserAlreadyExistsException> { authenticationService.signUp(command) }

        assertEquals("User already exists.", exception.message)
        verify { userRepository.findByEmailAndPassword(command.email, command.password) }
    }

    @Test
    fun `signUp should throw PasswordMismatchException when passwords do not match`() {
        val command = AuthenticationSignUpCommand(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "InvalidPass123!",
            profileImageUrl = null
        )

        every { userRepository.findByEmailAndPassword(any(), any()) } returns null

        val exception = assertThrows<PasswordMismatchException> {
            authenticationService.signUp(command)
        }

        assertEquals("Passwords do not match.", exception.message)
        verify(exactly = 0) { userRepository.create(any()) }
    }

    @Test
    fun `signIn should throw InvalidEmailException when email is invalid`() {
        val email = "invalid-email"
        val password = "ValidPass123!"

        val exception = assertThrows<InvalidEmailException> {
            authenticationService.signIn(email, password)
        }

        assertEquals("Invalid email address.", exception.message)
        verify(exactly = 0) { userRepository.findByEmailAndPassword(any(), any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password is too short`() {
        val email = "test@example.com"
        val password = "short"

        val exception = assertThrows<PasswordPolicyViolationException> {
            authenticationService.signIn(email, password)
        }

        assertEquals("Password must have at least eight characters.", exception.message)
        verify(exactly = 0) { userRepository.findByEmailAndPassword(any(), any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password does not contain any digit`() {
        val email = "test@example.com"
        val password = "password"

        val exception = assertThrows<PasswordPolicyViolationException> {
            authenticationService.signIn(email, password)
        }

        assertEquals("Password must contain at least one digit.", exception.message)
        verify(exactly = 0) { userRepository.findByEmailAndPassword(any(), any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password does not contain an uppercase letter`() {
        val email = "test@example.com"
        val password = "pa$\$w0rd"

        val exception = assertThrows<PasswordPolicyViolationException> {
            authenticationService.signIn(email, password)
        }

        assertEquals("Password must have at least one uppercase letter.", exception.message)
        verify(exactly = 0) { userRepository.findByEmailAndPassword(any(), any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password does not contain a special character`() {
        val email = "test@example.com"
        val password = "Passw0rd"

        every { userRepository.findByEmailAndPassword(any(), any()) } returns null

        val exception = assertThrows<PasswordPolicyViolationException> {
            authenticationService.signIn(email, password)
        }

        assertEquals("Password must have at least one special character, such as: _%-=+#@.", exception.message)
        verify(exactly = 0) { userRepository.findByEmailAndPassword(any(), any()) }
    }
}
