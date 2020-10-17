package com.everdonor.everdonorbackend.security

object SecurityConstants {
    const val SECRET = "SecretKeyToGenJWTs"
    const val EXPIRATION_TIME: Long = 864000000 // 10 days
    const val TOKEN_PREFIX = "Bearer "
    const val HEADER_STRING = "Authorization"
    const val SIGN_UP_URL = "/sign-up"
    const val LOGIN_URL = "/login"
    const val UPDATEPASSWORD_URL = "/updatePassword"
    const val SWAGGER_UI = "/swagger-ui/**"
    const val SWAGGER_RES = "/swagger-resources/**"
    const val SWAGGER_UI_INDEX = "/swagger-ui/index.html**"
    const val WEBJARS = "/webjars/**"
    const val ACTUATOR = "/actuator/health"
    const val SWAGGER_V2 = "/v2/api-docs"
    const val SWAGGER_V3 = "/v3/api-docs"
}