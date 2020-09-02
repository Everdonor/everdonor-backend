package com.everdonor.everdonorbackend.persistence.user

import org.springframework.data.repository.CrudRepository

import com.everdonor.everdonorbackend.model.User

public interface UserDAO : CrudRepository<User?, Int?>