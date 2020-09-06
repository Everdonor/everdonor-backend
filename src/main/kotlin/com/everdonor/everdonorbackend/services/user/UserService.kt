package com.everdonor.everdonorbackend.services.user

import com.everdonor.everdonorbackend.model.User
import java.util.*

interface UserService {
    fun createUser(user: User): Long?
    fun getAllUsers(): List<User?>
    fun getUsersByName(name:String): List<User?>
}