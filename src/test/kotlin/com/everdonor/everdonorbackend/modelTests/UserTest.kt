package com.everdonor.everdonorbackend.modelTests

import com.everdonor.everdonorbackend.model.User

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UserTest {
    @Test
    fun newUserNamedPhillip() {
        var user = User(1,"Phillip", "phillip@email.com", 1131122222)

        Assertions.assertEquals("Phillip", user.name)
        Assertions.assertEquals("phillip@email.com", user.email)
        Assertions.assertEquals(1131122222, user.phoneNumber)
    }
}