package com.everdonor.everdonorbackend.services.user

import com.everdonor.everdonorbackend.model.User

interface UserService {
    fun createUser(user: User): Long?
    fun getAllUsers(): List<User?>
}