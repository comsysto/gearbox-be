package de.comsystoreply.gearbox.bff.config

import de.comsystoreply.gearbox.domain.blog.port.api.BlogException
import de.comsystoreply.gearbox.domain.user.port.api.UserException
import de.comsystoreply.gearbox.domain.user.port.api.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

//TODO: Remove domain dependency and replace it with application exception
@ControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException::class)
    fun handleUserNotFoundException(ex: UserNotFoundException): ResponseEntity<ExceptionMessage> {
        val exceptionMessage = ExceptionMessage(
            message = ex.message,
        )
        return ResponseEntity(exceptionMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleAuthenticationException(ex: UserException): ResponseEntity<ExceptionMessage> {
        val exceptionMessage = ExceptionMessage(
            message = ex.message,
        )
        return ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleBlogExceptions(ex: BlogException): ResponseEntity<ExceptionMessage> {
        val exceptionMessage = ExceptionMessage(
            message = ex.message,
        )
        return ResponseEntity(exceptionMessage, HttpStatus.BAD_REQUEST)
    }
}

data class ExceptionMessage(
    val timestamp: LocalDateTime = LocalDateTime.now(),
    val message: String?
)