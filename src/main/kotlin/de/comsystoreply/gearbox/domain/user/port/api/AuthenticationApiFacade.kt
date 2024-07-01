package de.comsystoreply.gearbox.domain.user.port.api

import de.comsystoreply.gearbox.domain.user.model.User

/**
 * Interface which when implemented, will provide email and password User authentication
 */
interface AuthenticationApiFacade {
    /**
     * Function gets user credentials, validate them and return the User if it exists
     *
     * @property [details] custom model class which contains email and password
     * @return found User object
     * @throws InvalidEmailException if [details] contains invalid email address
     * @throws PasswordPolicyViolationException if [details] password doesn't meet all password requirements
     */
    fun signIn(details: AuthenticationDetails): User

    /**
     * Function gets new user data, validate credentials, checks if user exists and returns the newly created User
     *
     * @property [details] custom model class which contains all data required for new user to be created
     * @return newly created User object
     * @throws InvalidEmailException if [details] contains invalid email address
     * @throws PasswordMismatchException if [details] password and confirmPassword do not match
     * @throws PasswordPolicyViolationException if [details] password doesn't meet all password requirements
     */
    fun signUp(details: AuthenticationDetails): User
}

data class AuthenticationDetails(
    val email: String,
    val username: String? = null,
    val password: String,
    val confirmPassword: String? = null,
    val profileImageUrl: String? = null
)

sealed class AuthenticationException(message: String) : Exception(message)

class UserNotFoundException(message: String) : AuthenticationException(message)

class UserAlreadyExistsException(message: String) : AuthenticationException(message)

class InvalidEmailException(message: String) : AuthenticationException(message)

class PasswordMismatchException(message: String) : AuthenticationException(message)

class PasswordPolicyViolationException(message: String) : AuthenticationException(message)

