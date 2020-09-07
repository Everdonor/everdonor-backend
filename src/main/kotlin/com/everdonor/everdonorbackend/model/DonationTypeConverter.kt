package com.everdonor.everdonorbackend.model

import com.everdonor.everdonorbackend.exceptions.InvalidDonationTypeException
import org.springframework.core.convert.converter.Converter
import org.springframework.stereotype.Component

@Component
class DonationTypeConverter : Converter<String?, DonationType?> {
    override fun convert(source: String): DonationType? {
        return try {
            DonationType.valueOf(source.toUpperCase())
        } catch (e: IllegalArgumentException) {
            DonationType.UNKNOWN
        }
    }

}