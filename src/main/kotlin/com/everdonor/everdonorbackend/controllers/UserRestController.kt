package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.exceptions.InvalidDonationTypeException
import com.everdonor.everdonorbackend.exceptions.InvalidPasswordException
import com.everdonor.everdonorbackend.exceptions.UserNotFoundException
import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(origins = ["*"])

@RestController
class UserRestController(
        private val userService: UserService,
        private val passwordEncoder: PasswordEncoder
) {

    @PostMapping(value = ["/sign-up"])
    fun signUp(@RequestBody user: User): ResponseEntity<Long> {
        user.password = passwordEncoder.encode(user.password)
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
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

    @GetMapping(value = ["/users"])
    fun getAllUsers() = userService.getAllUsers()

    @GetMapping(value = ["/users"], params = ["types"])
    fun getUsersByTypes(@RequestParam types: List<DonationType>): List<User?> {
        if (types.isEmpty() || types.contains(DonationType.UNKNOWN))
            throw InvalidDonationTypeException("Donation type is invalid")
        else {
            return userService.getUsersByTypesIn(types)
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
    fun getUserByName(@RequestParam name: String) = userService.getUsersByName(name)

    @GetMapping(value = ["/users"], params = ["latitude", "longitude", "distance"])
    fun getUserByRadius(@RequestParam latitude: Double,
                        @RequestParam longitude: Double,
                        @RequestParam distance: Int) = userService.getUsersByRadius(latitude, longitude, distance)

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

    //TODO: WIP - Do not use
    @GetMapping(value = ["/users"], params = ["types", "name", "latitude", "longitude", "distance"])
    fun getUsersByTypesNameAndOrRadius(@RequestParam(required = false) types: List<DonationType>,
                                       @RequestParam(required = false) name: String,
                                       @RequestParam(required = false) latitude: Double,
                                       @RequestParam(required = false) longitude: Double,
                                       @RequestParam(required = false) distance: Int): List<User?> {
        return userService.getUsersByRadius(latitude, longitude, distance)
                .filter { user -> user!!.name.contains(name).and(user.donationTypes.any { it in types }) }
    }

}