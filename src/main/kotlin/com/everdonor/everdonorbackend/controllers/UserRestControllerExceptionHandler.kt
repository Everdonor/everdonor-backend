package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.exceptions.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.*

@ControllerAdvice
class UserRestControllerExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [(InvalidDonationTypeException::class)])
    fun handleInvalidDonationTypeException(exception: InvalidDonationTypeException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorsDetails = ErrorsDetails(Date(), "Donation Type not included", exception.message!!)
        return ResponseEntity(errorsDetails, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(value = [(UserNotFoundException::class)])
    fun handleUserNotFoundException(exception: UserNotFoundException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorsDetails = ErrorsDetails(Date(), "User ID not found", exception.message!!)
        return ResponseEntity(errorsDetails, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(value = [(UserAlreadyRegisteredException::class)])
    fun handleUserAlreadyRegisteredException(exception: UserAlreadyRegisteredException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorsDetails = ErrorsDetails(Date(), "User is already registered", exception.message!!)
        return ResponseEntity(errorsDetails, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(value = [(InvalidPasswordException::class)])
    fun handleInvalidPasswordException(exception: InvalidPasswordException, request: WebRequest): ResponseEntity<ErrorsDetails> {
        val errorsDetails = ErrorsDetails(Date(), "Password is invalid", exception.message!!)
        return ResponseEntity(errorsDetails, HttpStatus.BAD_REQUEST)
    }
}