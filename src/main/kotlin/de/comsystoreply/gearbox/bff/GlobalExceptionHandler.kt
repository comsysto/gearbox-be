package de.comsystoreply.gearbox.bff

import de.comsystoreply.gearbox.domain.user.port.application.AuthenticationException
import de.comsystoreply.gearbox.domain.user.port.application.UserNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.LocalDateTime

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
    fun handleAuthenticationException(ex: AuthenticationException): ResponseEntity<ExceptionMessage> {
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