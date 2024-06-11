package de.comsystoreply.gearbox.domain.user.adapter.application

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.application.UserService
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserServiceImplTest {

    private lateinit var userService: UserService
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        userRepository = mockk()
        userService = UserServiceImpl(userRepository)
    }

    @Test
    fun `findByEmailAndPassword should return User when email and password combination exists`() {
        val email = "test@test.de"
        val password = "test"
        val expectedUser =
            User(id = "id", email = email, username = "testuser", password = password, profileImageUrl = null)

        every { userRepository.findByEmailAndPassword(email, password) } returns expectedUser

        val actualUser = userService.findByEmailAndPassword(email, password)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `findByEmailAndPassword should return null when email and password combination doesn't exist`() {
        val email = "test@test.de"
        val password = "test"

        every { userRepository.findByEmailAndPassword(email, password) } returns null

        val actualUser = userService.findByEmailAndPassword(email, password)

        assertNull(actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }
}