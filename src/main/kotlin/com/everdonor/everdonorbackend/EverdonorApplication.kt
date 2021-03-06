package com.everdonor.everdonorbackend

import com.everdonor.everdonorbackend.controllers.UserRestController
import com.everdonor.everdonorbackend.model.DonationType
import com.everdonor.everdonorbackend.model.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import springfox.documentation.swagger2.annotations.EnableSwagger2


@SpringBootApplication
@EnableSwagger2
class EverdonorApplication

fun main(args: Array<String>) {
    runApplication<EverdonorApplication>(*args)
}

@Component
internal class DBPreloader {

    @Autowired
    private val userRestController: UserRestController? = null

    @EventListener
    fun appReady(event: ApplicationReadyEvent?) {
        //Users to Preload
        userRestController!!.signUp(User(1, "Comedor Nuevo", "comedor_nuevo@gmail.com",
                "totallyNewNotTheifablePassword", 1131110288, "Ayacucho 2002",
                -58.31F, -34.7245F, "https://elsolnoticias.com.ar/wp-content/uploads/2018/04/COme110418.jpg",
                listOf(DonationType.FOOD), "https://forms.todopago.com.ar:443/formulario/commands?command=formulario&fr=1&m=0cfa2dd44a53cfbd565a284b32236771#utm_source=3910474&utm_medium=boton_de_pago&utm_campaign=web"))
        userRestController.signUp(User(2, "Centro de donaciones Donarte", "donarte@gmail.com",
                "pass123", 1131112345, "Vicente Lopez 49",
                -58.3F, -34.72F,
                "https://i0.wp.com/updatemexico.com/wp-content/uploads/2020/06/WhatsApp-Image-2020-06-11-at-16.44.11.jpeg?fit=1125%2C633&ssl=1",
                listOf(DonationType.FUNDING),"",9))
        userRestController.signUp(User(3, "Fundación personas", "fundacion_personas@gmail.com",
                "pass123", 1131167890, "Mendoza 49",
                -58.334F, -34.71F,
                "https://media.lacapital.com.ar/adjuntos/203/imagenes/028/670/0028670283.jpg",
                listOf(DonationType.CLOTHES)))
        userRestController.signUp(User(4, "Comedor Quilmes", "comedor_quilmes@gmail.com",
                "pass123", 1131110293, "Guayaquil 187",
                -60.298F, -36.75F,
                "https://lh3.googleusercontent.com/proxy/LeWdvf6LdjYBabt-26pYxYOGVkO2xoYPrFwlEeg78d2FHm88OI_Zfho6b1-qYvcjp8tGQJ9ihsk2hxvuPkb_44CzgCWzbD1VlNGxSO_Or5tF1FRA-Wl9X879JXPNz_cbAEdoD2RQ64Uuh5Aphg",
                listOf(DonationType.FOOD, DonationType.CLOTHES))
        )
        userRestController.signUp(User(5, "Comedor Jueguetes", "comedor_Juguetes@gmail.com",
                "pass123", 1131110293, "Address 123",
                -58.298F, -34.75F,
                "https://lh3.googleusercontent.com/proxy/LeWdvf6LdjYBabt-26pYxYOGVkO2xoYPrFwlEeg78d2FHm88OI_Zfho6b1-qYvcjp8tGQJ9ihsk2hxvuPkb_44CzgCWzbD1VlNGxSO_Or5tF1FRA-Wl9X879JXPNz_cbAEdoD2RQ64Uuh5Aphg",
                listOf(DonationType.TOYS))
        )
        userRestController.signUp(User(6, "Comedor Kids", "comedor_Kids@gmail.com",
                "pass123", 1131110293, "Address 123",
                -55.298F, -31.75F,
                "https://lh3.googleusercontent.com/proxy/LeWdvf6LdjYBabt-26pYxYOGVkO2xoYPrFwlEeg78d2FHm88OI_Zfho6b1-qYvcjp8tGQJ9ihsk2hxvuPkb_44CzgCWzbD1VlNGxSO_Or5tF1FRA-Wl9X879JXPNz_cbAEdoD2RQ64Uuh5Aphg",
                listOf(DonationType.PRIMARY))
        )
        userRestController!!.signUp(User(7, "Comedor de Tarija", "comedor_tarija@gmail.com",
                "pass123", 1132823363, "Tarija 258",
                -58.2348457F, -34.8422006F, "https://imgur.com/JKZ8wni",
                listOf(DonationType.FOOD), ""))
        userRestController.signUp(User(8, "Los Chavitos", "los_chavitos@gmail.com",
                "pass123", 1131112345, "Brochero 360",
                -58.2325084F, -34.8408748F,
                "https://imgur.com/apm1yON",
                listOf(DonationType.FUNDING)))
        userRestController.signUp(User(9, "Rayito de luz", "rayito@gmail.com",
                "pass123", 1131112345, "Pergamino 585",
                -58.2330696F, -34.8377128F,
                "https://imgur.com/5fRyl9Y",
                listOf(DonationType.PRIMARY)))
    }
}