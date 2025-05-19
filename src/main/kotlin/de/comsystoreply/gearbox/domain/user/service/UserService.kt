package de.comsystoreply.gearbox.domain.user.service

import de.comsystoreply.gearbox.domain.user.model.User
import de.comsystoreply.gearbox.domain.user.port.api.*
import de.comsystoreply.gearbox.domain.user.port.persistance.CloudImageStorage
import de.comsystoreply.gearbox.domain.user.port.persistance.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository,
    private val cloudImageStorage: CloudImageStorage
) : UserApiFacade {

    override fun findByEmailAndPassword(email: String, password: String): User =
        userRepository.findByEmailAndPassword(email, password) ?: throw UserNotFoundException("User is not found.")

    override fun findByEmail(email: String): User =
        userRepository.findByEmail(email) ?: throw UserNotFoundException("User is not found.")

    override fun findById(id: String): User =
        userRepository.findById(id) ?: throw UserNotFoundException("User is not found.")

    override fun search(query: String, pageable: Pageable): Page<User> = userRepository.search(query, pageable)

    override fun signIn(details: UserInputDetails): User {
        validateBasicCredentials(details.email, details.password)

        return userRepository.findByEmailAndPassword(details.email, details.password)
            ?: throw UserNotFoundException("User is not found.")
    }

    override fun signUp(details: UserInputDetails): User {
        validateUserDoesntExist(details)
        validatePasswordMatching(details)
        validateBasicCredentials(details.email, details.password)

        val user = User(
            id = UUID.randomUUID().toString(),
            email = details.email,
            username = details.username!!,
            password = details.password,
            profileImageUrl = details.profileImageUrl
        )

        return userRepository.create(user)
    }

    override fun save(user: User): User {
        if (userRepository.findById(user.id) == null) {
            throw UserNotFoundException("User is not found.")
        }

        return userRepository.save(user)
    }

    override fun setProfileImage(
        actorUserId: String,
        profileUserId: String,
        imageBytes: ByteArray,
        contentType: String?
    ): User {
        val user = getValidatedUser(actorUserId, profileUserId)

        if (actorUserId != profileUserId) {
            throw UserForbiddenException("Action is forbidden.")
        }

        val imageUrl = cloudImageStorage.uploadImage(imageBytes, contentType = contentType)
        user.profileImageUrl = imageUrl

        return userRepository.save(user)
    }

    private fun validateBasicCredentials(email: String, password: String) {
        val emailResult = CredentialsValidator.validateEmail(email)
        val passwordResult = CredentialsValidator.validatePassword(password)

        if (emailResult.isFailure) {
            throw InvalidEmailException(emailResult.exceptionOrNull()!!.message!!)
        }

        if (passwordResult.isFailure) {
            throw PasswordPolicyViolationException(passwordResult.exceptionOrNull()!!.message!!)
        }
    }

    private fun validateUserDoesntExist(details: UserInputDetails) {
        val userWithSameEmail = userRepository.findByEmail(details.email)
        val userWithSameUsername = userRepository.findByUsername(details.email)

        if (userWithSameEmail != null || userWithSameUsername != null) {
            throw UserAlreadyExistsException("User already exists.")
        }
    }

    private fun validatePasswordMatching(details: UserInputDetails) {
        val passwordsDoNotMatch = details.password != details.confirmPassword
        if (passwordsDoNotMatch) {
            throw PasswordMismatchException("Passwords do not match.")
        }
    }

    private fun getValidatedUser(actorUserId: String, profileUserId: String): User {
        val user = userRepository.findById(actorUserId)
        if (user == null || userRepository.findById(profileUserId) == null) {
            throw UserNotFoundException("User is not found.")
        }
        return user
    }
}

private class CredentialsValidator {
    companion object {
        private val emailExpression = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$".toRegex()

        private const val ERR_EMAIL = "Invalid email address."
        private const val ERR_LEN = "Password must have at least eight characters."
        private const val ERR_DIGIT = "Password must contain at least one digit."
        private const val ERR_UPPER = "Password must have at least one uppercase letter."
        private const val ERR_SPECIAL = "Password must have at least one special character, such as: _%-=+#@."

        fun validateEmail(email: String): Result<Unit> {
            return runCatching { require(email.matches(emailExpression)) { ERR_EMAIL } }
        }

        fun validatePassword(password: String): Result<Unit> {
            return runCatching {
                require(password.length >= 8) { ERR_LEN }
                require(password.any { it.isDigit() }) { ERR_DIGIT }
                require(password.any { it.isUpperCase() }) { ERR_UPPER }
                require(password.any { !it.isLetterOrDigit() }) { ERR_SPECIAL }
            }
        }
    }
}