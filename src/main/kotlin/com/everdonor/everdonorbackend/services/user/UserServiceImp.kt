package com.everdonor.everdonorbackend.services.user


import com.everdonor.everdonorbackend.exceptions.UserAlreadyRegisteredException
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
class UserServiceImp @Autowired constructor(private val userDao: UserDAO) : UserService, UserDetailsService {

    @Throws(UserAlreadyRegisteredException::class)
    override fun createUser(user: User): Long? {
        userDao.findByEmail(user.email).ifPresent { throw UserAlreadyRegisteredException("User ${user.name}, ${user.email} is already registered.") }
        return userDao.save(user).id
    }

    override fun getUsersByRadius(latitude:Double,longitude:Double,distance:Int): List<User?> {
        return userDao.findByRadius(latitude,longitude,distance)
    }

    override fun getUsersByTypesInAndName(types: List<DonationType>, name: String): List<User?> {
        return userDao.findByDonationTypesInAndNameContaining(types, name)
    }


    override fun getUserById(id: Long): Optional<User?> {
        return userDao.findById(id)
    }

    override fun reportUser(id: Long) {
        val user = userDao.findById(id).get()
        user.report()
        if( user.reportQuantity > 10){
            user.softDelete()
        }
        userDao.save(user)

    }

    override fun updateUser(user: User): User {
        return userDao.save(user)
    }

    @Transactional(readOnly = true)
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(email: String): UserDetails {
        val optionalUser = userDao.findByEmail(email)
        val user: User
        if (!optionalUser.isPresent)
            throw UsernameNotFoundException(email)
        else
            user = optionalUser.get()
        return org.springframework.security.core.userdetails.User(user.email, user.password, emptyList())
    }

}