package com.everdonor.everdonorbackend.persistence.user

import com.everdonor.everdonorbackend.model.DonationType
import org.springframework.data.repository.CrudRepository

import com.everdonor.everdonorbackend.model.User
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface UserDAO : CrudRepository<User?, Int?> {
    fun findByDonationType(donationType: DonationType): List<User>
    fun findById(id: Long): Optional<User?>
    fun findAllByNameContaining (name:String):List<User?>
    fun findByEmail(email: String): Optional<User?>
}