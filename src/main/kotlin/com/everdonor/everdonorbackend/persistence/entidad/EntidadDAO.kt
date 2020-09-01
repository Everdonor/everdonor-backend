package com.everdonor.everdonorbackend.persistence.entidad

import org.springframework.data.repository.CrudRepository

import com.everdonor.everdonorbackend.model.Entidad

public interface EntidadDAO : CrudRepository<Entidad?, Int?>