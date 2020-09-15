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
        @Column(nullable = false)
        var password: String,
        var phoneNumber: Int,
        var longitude: Float,
        var latitude: Float,
        @Column(columnDefinition="TEXT")
        var image: String,
        @Enumerated(EnumType.STRING)
        var donationType: DonationType) {

}