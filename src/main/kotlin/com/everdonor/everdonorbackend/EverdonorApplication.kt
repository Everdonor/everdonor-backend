package com.everdonor.everdonorbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EverdonorApplication

fun main(args: Array<String>) {
	runApplication<EverdonorApplication>(*args)
}
