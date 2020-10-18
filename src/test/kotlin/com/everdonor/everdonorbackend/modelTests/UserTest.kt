package com.everdonor.everdonorbackend.modelTests

import com.everdonor.everdonorbackend.model.DonationType.FOOD
import com.everdonor.everdonorbackend.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun newUserNamedPhillip() {
        val user = User(1, "Phillip", "phillip@email.com",
                "password",1131122222,"address 1234",
                37.77F, 122.23F, "imageUrl", listOf(FOOD),"",0)

        assertEquals("Phillip", user.name)
        assertEquals("phillip@email.com", user.email)
        assertEquals("password",user.password)
        assertEquals(1131122222, user.phoneNumber)
        assertEquals("address 1234", user.address)
        assertEquals(37.77F, user.longitude)
        assertEquals(122.23F, user.latitude)
        assertEquals("imageUrl", user.image)
        assertEquals(listOf(FOOD), user.donationTypes)
    }
}