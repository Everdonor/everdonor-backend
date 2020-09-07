package com.everdonor.everdonorbackend.services.user


import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.persistence.user.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImp @Autowired constructor(userDao: UserDAO) : UserService {
    private val userDao: UserDAO = userDao

    override fun createUser(user:User): Long? {
        return userDao.save(user).id
    }

    override fun getAllUsers(): List<User?> {
        return userDao.findAll().toList()
    }

    override fun getUsersByType(donationType: DonationType): List<User?> {
        return userDao.findByDonationType(donationType)
    }

    override fun getUserById(id: Long): Optional<User?> {
        return userDao.findById(id)
    }


    /*init {
        this.businessDAO = businessDAO
        this.notificationService = notificationService
    }*/
}