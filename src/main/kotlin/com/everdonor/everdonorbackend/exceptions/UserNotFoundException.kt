package com.everdonor.everdonorbackend.exceptions

class UserNotFoundException(override val message: String?) : Exception(message)