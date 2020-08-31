package com.everdonor.everdonorbackend.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {
    @GetMapping(value = ["/test"])
    fun hello(): String {
        return "Hello World!!"
    }
}