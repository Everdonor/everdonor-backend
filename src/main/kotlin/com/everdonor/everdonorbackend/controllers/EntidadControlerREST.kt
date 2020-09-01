package com.everdonor.everdonorbackend.controllers

import com.everdonor.everdonorbackend.model.Entidad
import com.everdonor.everdonorbackend.services.entidad.EntidadService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin

@RequestMapping("/entidad")
class EntidadControlerREST(private val entidadService: EntidadService) {

    @PostMapping
    fun create(@RequestBody entidad: Entidad): ResponseEntity<Long> {
        return ResponseEntity(entidadService.createEntidad(entidad), HttpStatus.CREATED)
    }

    @GetMapping
    fun getAll() = entidadService.getallEntidades()



}