package com.everdonor.everdonorbackend.modelTests

import com.everdonor.everdonorbackend.controllers.UserRestController
import com.everdonor.everdonorbackend.exceptions.UserAlreadyRegisteredException
import com.everdonor.everdonorbackend.exceptions.UserNotFoundException
import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext

@SpringBootTest(webEnvironment = RANDOM_PORT)
class UserRestControllerTest {

    @LocalServerPort
    private val port: Int? = null

    @Autowired
    private val userRestController: UserRestController? = null

    /*
    {
        "id": 1,
        "name": "Centro de donaciones Donarte",
        "email": "donarte@gmail.com",
        "phoneNumber": 1131112345,
        "address": "Address 123",
        "longitude": -58.3,
        "latitude": -34.72,
        "image": "https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1",
        "donationType": "FUNDING"
    }
     */

//    @AfterEach
//    fun cleanUpDatabase() {
//
//    }

    @Test
    @Throws(Exception::class)
    fun fetchingCentroDeDonacionesDonarte() {
        val fetchedUser = userRestController!!.getUserById(1)
        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
        assertEquals("donarte@gmail.com", fetchedUser.email)
        assertEquals("pass123", fetchedUser.password)
        assertEquals(1131112345, fetchedUser.phoneNumber)
        assertEquals("Address 123", fetchedUser.address)
        assertEquals(-58.3F, fetchedUser.longitude)
        assertEquals(-34.72F, fetchedUser.latitude)
        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
        assertEquals(DonationType.FUNDING, fetchedUser.donationType)
    }


    @Test
    @Throws(Exception::class)
    fun report() {
        repeat(10) {
            userRestController!!.reportUser(1)
        }
        val fetchedUser = userRestController!!.getUserById(1)
        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
        assertEquals("donarte@gmail.com", fetchedUser.email)
        assertEquals("pass123", fetchedUser.password)
        assertEquals(1131112345, fetchedUser.phoneNumber)
        assertEquals("Address 123", fetchedUser.address)
        assertEquals(-58.3F, fetchedUser.longitude)
        assertEquals(-34.72F, fetchedUser.latitude)
        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
        assertEquals(DonationType.FUNDING, fetchedUser.donationType)
        assertEquals(10, fetchedUser.reportQuantity)

        userRestController!!.reportUser(1)

        assertThrows<UserNotFoundException> { userRestController!!.getUserById(1) }

    }

    /*
    {
        "name": "Comedor Nuevo",
        "email": "comedor_nuevo@gmail.com",
        "password": "totallyNewNotTheifablePassword",
        "phoneNumber": 1131110288,
        "address": "Address 123",
        "longitude": -58.288,
        "latitude": -34.65,
        "image": "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
        "donationType": "FOOD"
    }
     */
    @Test
    @DirtiesContext
    fun registeringANewUserNamedComedorNuevo() {
        val newUser = User(4, "Comedor Nuevo", "comedor_nuevo@gmail.com",
                "totallyNewNotTheifablePassword", 1131110288, "Address 123",
                -58.288F, -34.65F, "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
                DonationType.FOOD,0)
        assertEquals(201,userRestController!!.signUp(newUser).statusCodeValue)

        val fetchedUser = userRestController.getUserById(4)
        assertEquals("Comedor Nuevo", fetchedUser.name)
        assertEquals("comedor_nuevo@gmail.com", fetchedUser.email)
        assertEquals(1131110288, fetchedUser.phoneNumber)
        assertEquals("Address 123", fetchedUser.address)
        assertEquals(-58.288F, fetchedUser.longitude)
        assertEquals(-34.65F, fetchedUser.latitude)
        assertEquals("https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg", fetchedUser.image)
        assertEquals(DonationType.FOOD, fetchedUser.donationType)
    }

    @Test
    fun ModifyAUser() {
        val fetchedUser = userRestController!!.getUserById(1)
        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
        assertEquals("donarte@gmail.com", fetchedUser.email)
        assertEquals("pass123", fetchedUser.password)
        assertEquals(1131112345, fetchedUser.phoneNumber)
        assertEquals("Address 123", fetchedUser.address)
        assertEquals(-58.3F, fetchedUser.longitude)
        assertEquals(-34.72F, fetchedUser.latitude)
        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
        assertEquals(DonationType.FUNDING, fetchedUser.donationType)

        val newUser = User(1, "Comedor Nuevo", "comedor_nuevo@gmail.com",
                "totallyNewNotTheifablePassword", 1131110288, "Address 123",
                -58.288F, -34.65F, "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
                DonationType.FOOD,0)
        assertEquals(200,userRestController!!.modify(newUser).statusCodeValue)

        val fetchedUserModified = userRestController.getUserById(1)
        assertEquals("Comedor Nuevo", fetchedUserModified.name)
        assertEquals("comedor_nuevo@gmail.com", fetchedUserModified.email)
        assertEquals(1131110288, fetchedUserModified.phoneNumber)
        assertEquals("Address 123", fetchedUserModified.address)
        assertEquals(-58.288F, fetchedUserModified.longitude)
        assertEquals(-34.65F, fetchedUserModified.latitude)
        assertEquals("https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg", fetchedUserModified.image)
        assertEquals(DonationType.FOOD, fetchedUserModified.donationType)
    }

    @Test
    @DirtiesContext
    fun registeringComedorNuevoAgainThrowsAnUserAlreadyRegisteredException() {
        registeringANewUserNamedComedorNuevo()
        assertThrows<UserAlreadyRegisteredException> { registeringANewUserNamedComedorNuevo() }
    }

}