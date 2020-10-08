package com.everdonor.everdonorbackend.modelTests

import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.persistence.user.UserDAO
import com.everdonor.everdonorbackend.services.user.UserService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@RunWith(SpringRunner::class)
@SpringBootTest
class UserRestControllerTest : RestControllerTestUtils() {

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

    @Autowired
    private val userDAO: UserDAO? = null

    @Test
    @DirtiesContext
    @Throws(Exception::class)
    fun fetchingCentroDeDonacionesDonarte() {
//        val uri = "/users/2"
//        val mvcResult = mvc!!.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()
//        val status = mvcResult.response.status
//        assertEquals(200, status)

//        val fetchedUser = mapFromJson(mvcResult.response.contentAsString, User::class.java)
        val fetchedUser = userDAO!!.findById(2L).get()
        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
        assertEquals("donarte@gmail.com", fetchedUser.email)
        assertEquals(1131112345, fetchedUser.phoneNumber)
        assertEquals("Address 123", fetchedUser.address)
        assertEquals(-58.3F, fetchedUser.longitude)
        assertEquals(-34.72F, fetchedUser.latitude)
        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
        assertEquals(listOf(DonationType.FUNDING), fetchedUser.donationTypes)
    }


//    @Test
//    @DirtiesContext
//    @Throws(Exception::class)
//    fun report() {
//        repeat(10) {
//            userRestController!!.reportUser(2)
//        }
//        val fetchedUser = userRestController!!.getUserById(2)
//        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
//        assertEquals("donarte@gmail.com", fetchedUser.email)
//        assertEquals(1131112345, fetchedUser.phoneNumber)
//        assertEquals("Address 123", fetchedUser.address)
//        assertEquals(-58.3F, fetchedUser.longitude)
//        assertEquals(-34.72F, fetchedUser.latitude)
//        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
//        assertEquals(listOf(DonationType.FUNDING), fetchedUser.donationTypes)
//        assertEquals(10, fetchedUser.reportQuantity)
//
//        userRestController.reportUser(2)
//
//        assertThrows<UserNotFoundException> { userRestController.getUserById(2) }
//
//    }
//
//    /*
//    {
//        "name": "Comedor Nuevo",
//        "email": "comedor_nuevo@gmail.com",
//        "password": "totallyNewNotTheifablePassword",
//        "phoneNumber": 1131110288,
//        "address": "Address 123",
//        "longitude": -58.288,
//        "latitude": -34.65,
//        "image": "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
//        "donationType": "FOOD"
//    }
//     */
//    @Test
//    @DirtiesContext
//    fun registeringANewUserNamedComedorNuevo() {
//        val newUser = User(5, "Comedor Nuevo 2", "comedor_nuevo2@gmail.com",
//                "totallyNewNotTheifablePassword", 1131110288, "Address 123",
//                -58.288F, -34.65F, "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
//                listOf(DonationType.FOOD), 0)
//        assertEquals(201, userRestController!!.signUp(newUser).statusCodeValue)
//
//        val fetchedUser = userRestController.getUserById(5)
//        assertEquals("Comedor Nuevo 2", fetchedUser.name)
//        assertEquals("comedor_nuevo2@gmail.com", fetchedUser.email)
//        assertEquals(1131110288, fetchedUser.phoneNumber)
//        assertEquals("Address 123", fetchedUser.address)
//        assertEquals(-58.288F, fetchedUser.longitude)
//        assertEquals(-34.65F, fetchedUser.latitude)
//        assertEquals("https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg", fetchedUser.image)
//        assertEquals(listOf(DonationType.FOOD), fetchedUser.donationTypes)
//    }
//
//    @Test
//    fun ModifyAUser() {
//        val fetchedUser = userRestController!!.getUserById(2)
//        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
//        assertEquals("donarte@gmail.com", fetchedUser.email)
//        assertEquals(1131112345, fetchedUser.phoneNumber)
//        assertEquals("Address 123", fetchedUser.address)
//        assertEquals(-58.3F, fetchedUser.longitude)
//        assertEquals(-34.72F, fetchedUser.latitude)
//        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
//        assertEquals(listOf(DonationType.FUNDING), fetchedUser.donationTypes)
//
//        val newUser = User(2, "Comedor Nuevo", "comedor_nuevo@gmail.com",
//                "totallyNewNotTheifablePassword", 1131110288, "Address 123",
//                -58.288F, -34.65F, "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
//                listOf(DonationType.FOOD), 0)
//        assertEquals(200, userRestController.modify(newUser, newUser.id).statusCodeValue)
//
//        val fetchedUserModified = userRestController.getUserById(2)
//        assertEquals("Comedor Nuevo", fetchedUserModified.name)
//        assertEquals("comedor_nuevo@gmail.com", fetchedUserModified.email)
//        assertEquals(1131110288, fetchedUserModified.phoneNumber)
//        assertEquals("Address 123", fetchedUserModified.address)
//        assertEquals(-58.288F, fetchedUserModified.longitude)
//        assertEquals(-34.65F, fetchedUserModified.latitude)
//        assertEquals("https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg", fetchedUserModified.image)
//        assertEquals(listOf(DonationType.FOOD), fetchedUserModified.donationTypes)
//    }
//
//    @Test
//    @DirtiesContext
//    fun registeringComedorNuevoAgainThrowsAnUserAlreadyRegisteredException() {
//        registeringANewUserNamedComedorNuevo()
//        assertThrows<UserAlreadyRegisteredException> { registeringANewUserNamedComedorNuevo() }
//    }

}