package de.comsystoreply.gearbox.domain.user.service

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.*
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorage
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class UserServiceTest {

    private lateinit var userRepository: UserRepository
    private lateinit var userService: UserService
    private lateinit var cloudImageStorage: CloudImageStorage

    @BeforeEach
    fun setUp() {
        userRepository = mockk()
        cloudImageStorage = mockk()
        userService = UserService(userRepository, cloudImageStorage)
    }

    @Test
    fun `findByEmailAndPassword should return User when email and password combination exists`() {
        val email = "test@test.de"
        val password = "test"
        val expectedUser = User("id", email, "testuser", password, null)

        every { userRepository.findByEmailAndPassword(email, password) } returns expectedUser

        val actualUser = userService.findByEmailAndPassword(email, password)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `findByEmailAndPassword should throw UserNotFoundException when email and password combination don't exist`() {
        val email = "test@test.de"
        val password = "test"

        every { userRepository.findByEmailAndPassword(email, password) } returns null

        val exception = assertThrows<UserNotFoundException> {
            userService.findByEmailAndPassword(email, password)
        }

        assertEquals("User is not found.", exception.message)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `signIn should return user when credentials are valid`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val details = UserInputDetails(email = email, password = password)
        val expectedUser = User("id", email, "testuser", password, null)

        every { userRepository.findByEmailAndPassword(email, password) } returns expectedUser

        val actualUser = userService.signIn(details)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `signIn should throw UserNotFoundException when user is not found`() {
        val email = "test@example.com"
        val password = "ValidPass123!"
        val details = UserInputDetails(email = email, password = password)

        every { userRepository.findByEmailAndPassword(email, password) } returns null

        val exception = assertThrows<UserNotFoundException> {
            userService.signIn(details)
        }

        assertEquals("User is not found.", exception.message)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `signIn should throw InvalidEmailException when email is invalid`() {
        val email = "invalid-email"
        val password = "ValidPass123!"
        val details = UserInputDetails(email = email, password = password)

        val exception = assertThrows<InvalidEmailException> {
            userService.signIn(details)
        }

        assertEquals("Invalid email address.", exception.message)
        verify(exactly = 0) { userRepository.findByEmail(any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password is too short`() {
        val email = "test@example.com"
        val password = "short"
        val details = UserInputDetails(email = email, password = password)

        val exception = assertThrows<PasswordPolicyViolationException> {
            userService.signIn(details)
        }

        assertEquals("Password must have at least eight characters.", exception.message)
        verify(exactly = 0) { userRepository.findByEmail(any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password does not contain any digit`() {
        val email = "test@example.com"
        val password = "password"
        val details = UserInputDetails(email = email, password = password)

        val exception = assertThrows<PasswordPolicyViolationException> {
            userService.signIn(details)
        }

        assertEquals("Password must contain at least one digit.", exception.message)
        verify(exactly = 0) { userRepository.findByEmail(any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password does not contain an uppercase letter`() {
        val email = "test@example.com"
        val password = "pa$\$w0rd"
        val details = UserInputDetails(email = email, password = password)

        val exception = assertThrows<PasswordPolicyViolationException> {
            userService.signIn(details)
        }

        assertEquals("Password must have at least one uppercase letter.", exception.message)
        verify(exactly = 0) { userRepository.findByEmail(any()) }
    }

    @Test
    fun `signIn should throw PasswordPolicyViolationException when password does not contain a special character`() {
        val email = "test@example.com"
        val password = "Passw0rd"
        val details = UserInputDetails(email = email, password = password)

        every { userRepository.findByEmail(any()) } returns null

        val exception = assertThrows<PasswordPolicyViolationException> {
            userService.signIn(details)
        }

        assertEquals("Password must have at least one special character, such as: _%-=+#@.", exception.message)
        verify(exactly = 0) { userRepository.findByEmail(any()) }
    }

    @Test
    fun `signUp should return user when credentials are valid`() {
        val details = UserInputDetails(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )
        val expectedUser = User(
            id = "id",
            email = details.email,
            username = details.username!!,
            password = details.password,
            profileImageUrl = details.profileImageUrl
        )

        every { userRepository.findByEmail(any()) } returns null
        every { userRepository.findByUsername(any()) } returns null
        every { userRepository.create(any()) } returns expectedUser

        val actualUser = userService.signUp(details)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.findByEmail(any()) }
        verify { userRepository.findByUsername(any()) }
        verify { userRepository.create(any()) }
    }

    @Test
    fun `signUp should throw UserAlreadyExistsException when user with same email exists`() {
        val details = UserInputDetails(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )
        val existingUser = User(
            id = "id",
            email = details.email,
            username = details.username!!,
            password = details.password,
            profileImageUrl = details.profileImageUrl
        )

        every { userRepository.findByEmail(any()) } returns existingUser
        every { userRepository.findByUsername(any()) } returns null
        val exception = assertThrows<UserAlreadyExistsException> { userService.signUp(details) }

        assertEquals("User already exists.", exception.message)
        verify { userRepository.findByEmail(any()) }
        verify { userRepository.findByUsername(any()) }
    }

    @Test
    fun `signUp should throw UserAlreadyExistsException when user with same username exists`() {
        val details = UserInputDetails(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "ValidPass123!",
            profileImageUrl = null
        )
        val existingUser = User(
            id = "id",
            email = details.email,
            username = details.username!!,
            password = details.password,
            profileImageUrl = details.profileImageUrl
        )

        every { userRepository.findByEmail(any()) } returns null
        every { userRepository.findByUsername(any()) } returns existingUser
        val exception = assertThrows<UserAlreadyExistsException> { userService.signUp(details) }

        assertEquals("User already exists.", exception.message)
        verify { userRepository.findByEmail(any()) }
        verify { userRepository.findByUsername(any()) }
    }

    @Test
    fun `signUp should throw PasswordMismatchException when passwords do not match`() {
        val command = UserInputDetails(
            email = "test@example.com",
            username = "testuser",
            password = "ValidPass123!",
            confirmPassword = "InvalidPass123!",
            profileImageUrl = null
        )

        every { userRepository.findByEmail(any()) } returns null
        every { userRepository.findByUsername(any()) } returns null

        val exception = assertThrows<PasswordMismatchException> {
            userService.signUp(command)
        }

        assertEquals("Passwords do not match.", exception.message)
        verify(exactly = 0) { userRepository.create(any()) }
    }
}