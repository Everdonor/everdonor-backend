package com.everdonor.everdonorbackend

import com.everdonor.everdonorbackend.model.User
import com.everdonor.everdonorbackend.persistence.user.UserDAO
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@SpringBootApplication
class EverdonorApplication

fun main(args: Array<String>) {
	runApplication<EverdonorApplication>(*args)
}

//@Configuration
//class LoadDatabase {
//	@Bean
//	fun initDatabase(repository: UserDAO) : CommandLineRunner {
//		return args -> {
//			log.info("Preloading ${repository.save(User())}")
//		}
//	}
//}