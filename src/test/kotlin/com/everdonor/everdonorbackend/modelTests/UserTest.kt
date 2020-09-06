package com.everdonor.everdonorbackend.modelTests

import com.everdonor.everdonorbackend.model.DonationType.FOOD
import com.everdonor.everdonorbackend.model.User
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun newUserNamedPhillip() {
        var user = User(1, "Phillip", "phillip@email.com",
                1131122222, 37.77F, 122.23F, FOOD)

        Assertions.assertEquals("Phillip", user.name)
        Assertions.assertEquals("phillip@email.com", user.email)
        Assertions.assertEquals(1131122222, user.phoneNumber)
        Assertions.assertEquals(37.77F, user.longitude)
        Assertions.assertEquals(122.23F, user.latitude)
        Assertions.assertEquals(FOOD, user.donationType)
    }
}