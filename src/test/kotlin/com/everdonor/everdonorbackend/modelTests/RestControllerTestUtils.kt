package com.everdonor.everdonorbackend.modelTests

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import java.io.IOException


abstract class RestControllerTestUtils {

    @Throws(JsonProcessingException::class)
    protected fun mapToJson(obj: Any?): String {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(obj)
    }

    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    protected fun <T> mapFromJson(json: String?, clazz: Class<T>?): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(json, clazz)
    }
}