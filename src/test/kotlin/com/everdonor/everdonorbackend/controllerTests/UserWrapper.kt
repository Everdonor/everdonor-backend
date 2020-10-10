package com.everdonor.everdonorbackend.controllerTests

import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

class UserWrapper(id: Long, name: String, email: String,
                  phoneNumber: Int, address: String, longitude: Float,
                  latitude: Float, image: String, donationTypes: Collection<DonationType>) :
        User(id, name, email, "password", phoneNumber, address, longitude, latitude, image, donationTypes) {

}