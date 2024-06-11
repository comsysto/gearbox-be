package de.comsystoreply.gearbox.domain.user.port.application

import de.comsystoreply.gearbox.domain.user.model.User

/**
 * Interface which when implemented, will provide email and password User authentication
 */
interface AuthenticationService {
    /**
     * This function gets user credentials, validate them and return the User if it exists
     *
     * @property [email] must be valid email address
     * @property [password] must be at least 8 characters long with a number and a special character
     * @return found User object
     * @throws InvalidEmailException if the [email] is not valid a valid email address
     * @throws PasswordPolicyViolationException if the [password] doesn't meet all password requirements
     */
    fun signIn(email: String, password: String): User

    /**
     * This function gets new user data, validate credentials, checks if user exists and returns the newly created User
     *
     * @property [authenticationSignUpCommand] custom model class which contains all data required for new user to be created
     * @return newly created User object
     * @throws InvalidEmailException if [authenticationSignUpCommand] contains invalid email address
     * @throws PasswordMismatchException if [authenticationSignUpCommand] password and confirmPassword do not match
     * @throws PasswordPolicyViolationException if [authenticationSignUpCommand] password doesn't meet all password requirements
     */
    fun signUp(authenticationSignUpCommand: AuthenticationSignUpCommand): User
}

data class AuthenticationSignUpCommand(
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String?,
    val profileImageUrl: String?
)

sealed class AuthenticationException(message: String) : Exception(message)

class UserNotFoundException(message: String) : AuthenticationException(message)

class InvalidEmailException(message: String) : AuthenticationException(message)

class PasswordMismatchException(message: String) : AuthenticationException(message)

class PasswordPolicyViolationException(message: String) : AuthenticationException(message)

