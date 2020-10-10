package com.everdonor.everdonorbackend.modelTests

import com.everdonor.everdonorbackend.controllerTests.UserWrapper
import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.web.client.MockRestServiceServer
import org.springframework.test.web.client.match.MockRestRequestMatchers.method
import org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo
import org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess
import org.springframework.web.client.RestTemplate


class UserRestControllerTest {

    private var mockServer: MockRestServiceServer? = null

    private var restTemplate: RestTemplate? = null

    @BeforeEach
    fun setup() {
        restTemplate = RestTemplate()
        mockServer = MockRestServiceServer.bindTo(restTemplate!!).ignoreExpectOrder(true).build()
    }

    @Test
    fun getUser() {
        val responseBody = "{\"id\":1,\"name\":\"Comedor Nuevo\",\"email\":\"comedor_nuevo@gmail.com\",\"phoneNumber\":1131110288,\"address\":\"Address 123\",\"longitude\":-58.288,\"latitude\":-34.65,\"image\":\"https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg\",\"donationTypes\":[\"FOOD\"]}"
        mockServer!!.expect(requestTo("/users/2")).andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(responseBody, MediaType.APPLICATION_JSON))
        val fetchedUser: UserWrapper? = restTemplate!!.getForObject("/users/{id}", UserWrapper::class.java, 2)

        assertEquals("Comedor Nuevo", fetchedUser!!.name)
        assertEquals("comedor_nuevo@gmail.com", fetchedUser.email)
        assertEquals(1131110288, fetchedUser.phoneNumber)
        assertEquals("Address 123", fetchedUser.address)
        assertEquals(-58.288F, fetchedUser.longitude)
        assertEquals(-34.65F, fetchedUser.latitude)
        assertEquals("https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg", fetchedUser.image)
        assertEquals(listOf(DonationType.FOOD), fetchedUser.donationTypes)
        mockServer!!.verify()
    }

//    @AfterEach
//    fun cleanUpDatabase() {
//
//    }

//    @Test
//    @DirtiesContext
//    @Throws(Exception::class)
//    fun fetchingCentroDeDonacionesDonarte() {
//        val fetchedUser = userRestController!!.getUserById(2)
//        assertEquals("Centro de donaciones Donarte", fetchedUser.name)
//        assertEquals("donarte@gmail.com", fetchedUser.email)
//        assertEquals(1131112345, fetchedUser.phoneNumber)
//        assertEquals("Address 123", fetchedUser.address)
//        assertEquals(-58.3F, fetchedUser.longitude)
//        assertEquals(-34.72F, fetchedUser.latitude)
//        assertEquals("https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1", fetchedUser.image)
//        assertEquals(listOf(DonationType.FUNDING), fetchedUser.donationTypes)
//    }
//
//
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
