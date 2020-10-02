package com.everdonor.everdonorbackend.security

object SecurityConstants {
    const val SECRET = "SecretKeyToGenJWTs"
    const val EXPIRATION_TIME: Long = 864000000 // 10 days
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val SIGN_UP_URL = "/sign-up"
    const val LOGIN_URL = "/login"
    const val UPDATEPASSWORD_URL = "/updatePassword"
}