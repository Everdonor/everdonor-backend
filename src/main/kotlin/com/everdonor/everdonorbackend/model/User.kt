package com.everdonor.everdonorbackend.model

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY
import org.hibernate.annotations.Where
import javax.persistence.*

@Entity
@Where(clause = "is_active=1")
open class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long,
        @Column(nullable = false, length = 500)
        var name: String,
        var email: String,
        @Column(nullable = false)
        @JsonProperty(access = WRITE_ONLY)
        var password: String,
        var phoneNumber: Int,
        var address: String,
        var longitude: Float,
        var latitude: Float,
        @Column(columnDefinition = "MEDIUMTEXT")
        var image: String,
        @ElementCollection(targetClass = DonationType::class, fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        @Column(name = "donation_type")
        var donationTypes: Collection<DonationType>,
        var todoPagoLink: String = "",
        @JsonProperty(access = WRITE_ONLY)
        var reportQuantity: Int = 0,
        @Column(name = "is_active")
        @JsonProperty(access = WRITE_ONLY)
        var active: Boolean = true,
        @ElementCollection
        var links: Collection<String> = listOf()
) {

    fun report(): Int {
        return this.reportQuantity++
    }

    fun softDelete() {
        this.active = false
    }
}