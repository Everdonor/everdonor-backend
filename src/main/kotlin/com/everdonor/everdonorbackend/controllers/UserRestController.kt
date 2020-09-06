package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.services.user.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin

@RestController
class UserRestController(private val userService: UserService) {

    @PostMapping(value = ["/user"])
    fun create(@RequestBody user: User): ResponseEntity<Long> {
        return ResponseEntity(userService.createUser(user), HttpStatus.CREATED)
    }

    @GetMapping(value = ["/users"])
    fun getAll() = userService.getAllUsers()



    @GetMapping(value = ["/searchUser"])
    fun getUserByName(@RequestParam q:String) = userService.getUsersByName(q)



}