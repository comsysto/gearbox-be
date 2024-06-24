package de.comsystoreply.gearbox.domain.user.domain

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UserServiceTest {

    private lateinit var userApiFacade: UserApiFacade
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp() {
        userRepository = mockk()
        userApiFacade = UserService(userRepository)
    }

    @Test
    fun `findByEmailAndPassword should return User when email and password combination exists`() {
        val email = "test@test.de"
        val password = "test"
        val expectedUser = User("id", email, "testuser", password, null)

        every { userRepository.findByEmailAndPassword(email, password) } returns expectedUser

        val actualUser = userApiFacade.findByEmailAndPassword(email, password)

        assertEquals(expectedUser, actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }

    @Test
    fun `findByEmailAndPassword should return null when email and password combination doesn't exist`() {
        val email = "test@test.de"
        val password = "test"

        every { userRepository.findByEmailAndPassword(email, password) } returns null

        val actualUser = userApiFacade.findByEmailAndPassword(email, password)

        assertNull(actualUser)
        verify { userRepository.findByEmailAndPassword(email, password) }
    }
}