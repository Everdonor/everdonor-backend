package com.everdonor.everdonorbackend.model

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

class DonationTypeDeserializer : JsonDeserializer<DonationType>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): DonationType? {
        val oc = p.codec
        val node = oc.readTree<JsonNode>(p) ?: return null

        val text = node.textValue() ?: return null

        return DonationType.valueOf(text.toUpperCase())
    }
}