package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin

@RequestMapping("/user")
class UserControlerREST(private val userService: UserService) {

    @PostMapping
    fun create(@RequestBody user: User): ResponseEntity<Long> {
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAll() = userService.getAllUsers()



}