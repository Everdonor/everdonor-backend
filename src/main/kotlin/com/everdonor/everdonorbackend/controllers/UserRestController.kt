package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.exceptions.InvalidPasswordException
import com.everdonor.everdonorbackend.exceptions.InvalidTodoPagoLiException
import com.everdonor.everdonorbackend.exceptions.UserNotFoundException
import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@CrossOrigin(origins = ["*"])

@RestController
class UserRestController(
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder
) {

    @PostMapping(value = ["/sign-up"])
    fun signUp(@RequestBody user: User): ResponseEntity<Long> {
        if (user.todoPagoLink == "" || user.todoPagoLink.contains("https://forms.todopago.com.ar")) {
            user.password = passwordEncoder.encode(user.password)
            return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
        } else {
            throw InvalidTodoPagoLiException("Todo Pago Link is invalid.")
        }
    }

    @PostMapping(value = ["/updatePassword"], params = ["newPassword", "oldPassword", "id"])
    fun updatePassword(@RequestParam("newPassword") newPassword: String,
                       @RequestParam("oldPassword") oldPassword: String,
                       @RequestParam("id") id: Long): ResponseEntity<User> {
        val user = userService.getUserById(id).get()
        if (passwordEncoder.matches(oldPassword, user.password)) {
            user.password = passwordEncoder.encode(newPassword)
            return ResponseEntity(userService.updateUser(user), HttpStatus.ACCEPTED)
        } else {
            throw InvalidPasswordException("Password is invalid")
        }
    }

    @GetMapping(value = ["/users/{id}"])
    fun getUserById(@PathVariable("id") id: Long): User {
        val user = userService.getUserById(id)
        if (user.isPresent)
            return user.get()
        else
            throw UserNotFoundException("User with id $id was not found")
    }

    @PutMapping(value = ["/users/{id}/report"])
    fun reportUser(@PathVariable("id") id: Long) {
        val user = userService.getUserById(id)
        if (user.isPresent)
            userService.reportUser(id)
        else
            throw UserNotFoundException("User with id $id was not found")
    }

    @PutMapping(value = ["/users/{id}"])
    fun modify(@RequestBody user: User, @PathVariable("id") id: Long): ResponseEntity<User> {
        return ResponseEntity(userService.updateUser(user), HttpStatus.OK)
    }


    //TODO: Add exceptions again
    @GetMapping(value = ["/users"])
    fun getUsersByTypesNameAndOrRadius(@RequestParam(required = false, defaultValue = "CLOTHES, FOOD, FUNDING") types: List<DonationType>,
                                       @RequestParam(required = false, defaultValue = "") name: String,
                                       @RequestParam(required = false, defaultValue = "0") latitude: Double,
                                       @RequestParam(required = false, defaultValue = "0") longitude: Double,
                                       @RequestParam(required = false, defaultValue = "100000") distance: Int
                                       ): List<User?> {
        return userService.getUsersByRadius(latitude, longitude, distance)
                .filter { user -> userService.getUsersByTypesInAndName(types, name).contains(user) }
    }

}