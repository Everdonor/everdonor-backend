package com.everdonor.everdonorbackend.services.user

import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import java.util.*

interface UserService {
    fun createUser(user: User): Long?
    fun getUserById(id: Long): Optional<User?>
    fun reportUser(id: Long)
    fun updateUser(user: User): User?
    fun getUsersByRadius(latitude:Double,longitude:Double,distance:Int): List<User?>
    fun getUsersByTypesInAndName(types: List<DonationType>, name: String): List<User?>
}