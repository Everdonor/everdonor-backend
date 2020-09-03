package com.everdonor.everdonorbackend.model

import javax.persistence.*

@Entity
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(nullable = false, length = 500)
        var name: String,
        var email: String,
        var phoneNumber: Int,
        var longitude: Float,
        var latitude: Float) {


}