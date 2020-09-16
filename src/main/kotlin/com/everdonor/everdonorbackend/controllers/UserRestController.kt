package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.exceptions.InvalidDonationTypeException
import com.everdonor.everdonorbackend.exceptions.UserNotFoundException
import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@CrossOrigin

@RestController
//@RequestMapping("/users")
class UserRestController(
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder
) {

    @PostMapping(value = ["/sign-up"])
    fun signUp(@RequestBody user: User): ResponseEntity<Long> {
        user.password = passwordEncoder.encode(user.password)
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

    //todo: verify how to accept multiple params in one request

    @GetMapping(value = ["/users"])
    fun getAllUsers() = userService.getAllUsers()

    @GetMapping(value = ["/users"], params = ["type"])
    fun getUsersByType(@RequestParam("type") donationType: DonationType): List<User?> {
        if (donationType == DonationType.UNKNOWN)
            throw InvalidDonationTypeException("Donation type is invalid")
        else {
            return userService.getUsersByType(donationType)
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

    @GetMapping(value = ["/users"], params = ["name"])
    fun getUserByName(@RequestParam name:String) = userService.getUsersByName(name)

    @PutMapping(value = ["/users/{id}/report"])
    fun reportUser(@PathVariable("id") id: Long) {
        val user = userService.getUserById(id)
        if (user.isPresent)
           userService.reportUser(id)
        else
            throw UserNotFoundException("User with id $id was not found")
    }

    @PutMapping(value = ["/users/{id}"])
    fun modify(@RequestBody user: User): ResponseEntity<User> {
        return ResponseEntity(userService.updateUser(user), HttpStatus.OK)
    }



}