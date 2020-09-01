package com.everdonor.everdonorbackend.services.user


import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.persistence.user.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class UserServiceImp @Autowired constructor(userDao: UserDAO) : UserService {
    private val userDao: UserDAO = userDao

    override fun createUser(user:User): Long? {
        return userDao.save(user).id
    }

    override fun getAllUsers(): List<User?> {
        return userDao.findAll().toList()
    }


    /*init {
        this.businessDAO = businessDAO
        this.notificationService = notificationService
    }*/
}