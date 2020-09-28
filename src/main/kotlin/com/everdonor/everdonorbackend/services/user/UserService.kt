package com.everdonor.everdonorbackend.services.user

import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import java.util.*

interface UserService {
    fun createUser(user: User): Long?
    fun getAllUsers(): List<User?>
    fun getUsersByName(name:String): List<User?>
    fun getUsersByType(donationType: DonationType): List<User?>
    fun getUserById(id: Long): Optional<User?>
    fun reportUser(id: Long)
    fun updateUser(user: User): User?
    fun getUsersByRadius(latitude:Double,longitude:Double,distance:Int): List<User?>
}