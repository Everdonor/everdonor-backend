package com.everdonor.everdonorbackend.exceptions

class UserAlreadyRegisteredException(override val message: String?) : Exception(message)