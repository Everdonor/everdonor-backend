package com.everdonor.everdonorbackend.persistence.user

import com.everdonor.everdonorbackend.model.DonationType
import org.springframework.data.repository.CrudRepository

import com.everdonor.everdonorbackend.model.User
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

interface UserDAO : CrudRepository<User?, Int?> {
    fun findById(id: Long): Optional<User?>
    fun findByEmail(email: String): Optional<User?>
    @Query("SELECT" +
            "  *, ROUND(" +
            "    6371 * acos (" +
            "      cos ( radians(:userLatitude) )" +
            "      * cos( radians( latitude ) )" +
            "      * cos( radians( longitude ) - radians(:userLongitude) )" +
            "      + sin ( radians(:userLatitude) )" +
            "      * sin( radians( latitude ) )" +
            "    )" +
            "  ) AS distance" +
            " FROM user" +
            " HAVING distance <= :userDistance" +
            " ORDER BY distance",nativeQuery = true)
    fun findByRadius(@Param("userLatitude")latitude:Double,
                     @Param("userLongitude")longitude:Double,
                     @Param("userDistance")distance:Int)
            : List<User?>

    fun findByDonationTypesInAndNameContaining(types: List<DonationType>, name: String): List<User?>

}