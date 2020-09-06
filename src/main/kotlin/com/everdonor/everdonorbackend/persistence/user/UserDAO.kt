package com.everdonor.everdonorbackend.persistence.user

import org.springframework.data.repository.CrudRepository

import com.everdonor.everdonorbackend.model.User

import java.util.*

interface UserDAO : CrudRepository<User, Long?>{
    fun findAllByNameContaining (name:String):List<User?>
}