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
        open val id: Long,
        @Column(nullable = false, length = 500)
        open var name: String,
        open var email: String,
        @Column(nullable = false)
        @JsonProperty(access = WRITE_ONLY)
        open var password: String,
        open var phoneNumber: Int,
        open var address: String,
        open var longitude: Float,
        open var latitude: Float,
        @Column(columnDefinition = "MEDIUMTEXT")
        open var image: String,
        @ElementCollection(targetClass = DonationType::class, fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        @Column(name = "donation_type")
        open var donationTypes: Collection<DonationType>,
        open var todoPagoLink: String = "",
        @JsonProperty(access = WRITE_ONLY)
        open var reportQuantity: Int = 0,
        @Column(name = "is_active")
        @JsonProperty(access = WRITE_ONLY)
        open var active: Boolean = true,
        @ElementCollection
        open var links: Collection<String> = listOf()
) {

    fun report(): Int {
        return this.reportQuantity++
    }

    fun softDelete() {
        this.active = false
    }
}