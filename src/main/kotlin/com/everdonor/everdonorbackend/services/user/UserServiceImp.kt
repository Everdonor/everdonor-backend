package com.everdonor.everdonorbackend.services.user


import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.persistence.user.UserDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*


@Service
class UserServiceImp @Autowired constructor(userDao: UserDAO) : UserService, UserDetailsService {
    private val userDao: UserDAO = userDao

    override fun createUser(user: User): Long? {
        return userDao.save(user).id
    }

    override fun getAllUsers(): List<User?> {
        return userDao.findAll().toList()
    }

    override fun getUsersByName(name: String): List<User?> {
        return userDao.findAllByNameContaining(name)
    }

    override fun getUsersByType(donationType: DonationType): List<User?> {
        return userDao.findByDonationType(donationType)
    }

    override fun getUserById(id: Long): Optional<User?> {
        return userDao.findById(id)
    }

    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userDao.findByEmail(email) ?: throw UsernameNotFoundException(email)
        return org.springframework.security.core.userdetails.User(user.email, user.password, emptyList())
    }

}