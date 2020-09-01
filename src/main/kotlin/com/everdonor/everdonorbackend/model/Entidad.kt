package com.everdonor.everdonorbackend.model


import javax.persistence.*

@Entity
class Entidad() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    @Column(nullable = false, length = 500)
    var nombre: String? = null
    var email: String?=null
    var telefono: Int?=null


    constructor(nombre: String,email:String,telefono :Int) : this() {
        this.nombre = nombre
        this.email=email
        this.telefono=telefono
    }


}