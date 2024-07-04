package de.comsystoreply.gearbox.domain.user.port.api

import de.comsystoreply.gearbox.domain.user.model.User

/**
 * User API interface which provides user search and fetching
 */
interface UserApiFacade {
    /**
     * @property [email] provided by user, must be valid email address
     * @property [password] provided by user
     * @return returns User domain object
     * @throws UserNotFoundException if user with given [email] and [password] is not found
     */
    fun findByEmailAndPassword(email: String, password: String): User

    /**
     * @property [email] provided by user, must be valid email address
     * @return returns User domain object
     * @throws UserNotFoundException if user with given [email] is not found
     */
    fun findByEmail(email: String): User

    /**
     * Function gets user credentials, validate them and return the User if it exists
     *
     * @property [details] custom model class which contains email and password
     * @return found User object
     * @throws InvalidEmailException if [details] contains invalid email address
     * @throws PasswordPolicyViolationException if [details] password doesn't meet all password requirements
     * @throws UserNotFoundException if [details] contains credentials that doesn't match with any user
     */
    fun signIn(details: UserInputDetails): User

    /**
     * Function gets new user data, validate credentials, checks if user exists and returns the newly created User
     *
     * @property [details] custom model class which contains all data required for new user to be created
     * @return newly created User object
     * @throws InvalidEmailException if [details] contains invalid email address
     * @throws PasswordMismatchException if [details] password and confirmPassword do not match
     * @throws PasswordPolicyViolationException if [details] password doesn't meet all password requirements
     * @throws UserAlreadyExistsException if [details] match with the existing details in repository
     */
    fun signUp(details: UserInputDetails): User
}

data class UserInputDetails(
    val email: String,
    val username: String? = null,
    val password: String,
    val confirmPassword: String? = null,
    val profileImageUrl: String? = null
)

sealed class UserException(message: String) : Exception(message)

class UserNotFoundException(message: String) : UserException(message)

class UserAlreadyExistsException(message: String) : UserException(message)

class InvalidEmailException(message: String) : UserException(message)

class PasswordMismatchException(message: String) : UserException(message)

class PasswordPolicyViolationException(message: String) : UserException(message)
