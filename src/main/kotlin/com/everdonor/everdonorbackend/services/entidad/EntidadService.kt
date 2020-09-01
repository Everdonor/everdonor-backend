package com.everdonor.everdonorbackend.services.entidad

import com.everdonor.everdonorbackend.model.Entidad

interface EntidadService {
    fun createEntidad(entidad:Entidad): Long?
    fun getallEntidades():List<Entidad?>
}