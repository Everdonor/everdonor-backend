package com.everdonor.everdonorbackend.model

import com.fasterxml.jackson.databind.annotation.JsonDeserialize

@JsonDeserialize(using = DonationTypeDeserializer::class)
enum class DonationType(var type: String) {
    FOOD("Food"),
    CLOTHES("Clothes"),
    FUNDING("Funding"),
    UNKNOWN("Unknown")
}
