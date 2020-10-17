package com.everdonor.everdonorbackend.security

import com.everdonor.everdonorbackend.security.SecurityConstants.ACTUATOR
import com.everdonor.everdonorbackend.security.SecurityConstants.LOGIN_URL
import com.everdonor.everdonorbackend.security.SecurityConstants.SIGN_UP_URL
import com.everdonor.everdonorbackend.security.SecurityConstants.SWAGGER_RES
import com.everdonor.everdonorbackend.security.SecurityConstants.SWAGGER_V2
import com.everdonor.everdonorbackend.security.SecurityConstants.SWAGGER_UI
import com.everdonor.everdonorbackend.security.SecurityConstants.SWAGGER_UI_INDEX
import com.everdonor.everdonorbackend.security.SecurityConstants.SWAGGER_V3
import com.everdonor.everdonorbackend.security.SecurityConstants.UPDATEPASSWORD_URL
import com.everdonor.everdonorbackend.security.SecurityConstants.WEBJARS
import com.everdonor.everdonorbackend.services.user.UserServiceImp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource


@EnableWebSecurity
class WebSecurity(@Autowired private val userService: UserServiceImp, @Autowired private val bCryptPasswordEncoder: BCryptPasswordEncoder) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SIGN_UP_URL).permitAll()
                .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                .antMatchers(HttpMethod.POST, UPDATEPASSWORD_URL).permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_UI).permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_RES).permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_UI_INDEX).permitAll()
                .antMatchers(HttpMethod.GET, WEBJARS).permitAll()
                .antMatchers(HttpMethod.GET, ACTUATOR).permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_V2).permitAll()
                .antMatchers(HttpMethod.GET, SWAGGER_V3).permitAll()
                .antMatchers("/users/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(JWTAuthenticationFilter(authenticationManager()))
                .addFilter(JWTAuthorizationFilter(authenticationManager())) // this disables session creation on Spring Security
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    }

    @Throws(Exception::class)
    public override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService<UserDetailsService>(userService).passwordEncoder(bCryptPasswordEncoder)
    }

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val corsConfiguration = CorsConfiguration().applyPermitDefaultValues()
        val source = UrlBasedCorsConfigurationSource()
        corsConfiguration.allowCredentials = true
        corsConfiguration.maxAge = 1800L
        corsConfiguration.addAllowedOrigin("http://localhost:3000")
        corsConfiguration.addAllowedHeader("*")
        corsConfiguration.addAllowedMethod("*")
        corsConfiguration.exposedHeaders = listOf("Authorization")
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source
    }

}