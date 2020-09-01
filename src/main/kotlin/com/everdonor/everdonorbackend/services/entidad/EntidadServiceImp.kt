package com.everdonor.everdonorbackend.services.entidad


import com.everdonor.everdonorbackend.model.Entidad
import com.everdonor.everdonorbackend.persistence.entidad.EntidadDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service



@Service
class EntidadServiceImp @Autowired constructor(entidadDao: EntidadDAO) : EntidadService {
    private val entidadDao: EntidadDAO = entidadDao

    override fun createEntidad(entidad:Entidad): Long? {
        return entidadDao.save(entidad).id
    }

    override fun getallEntidades(): List<Entidad?> {
        return entidadDao.findAll().toList()
    }


    /*init {
        this.businessDAO = businessDAO
        this.notificationService = notificationService
    }*/
}