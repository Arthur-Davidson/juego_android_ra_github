package mx.uacj.juegora.repositoriosEstaticos.estaticos.estaticos

import android.location.Location
import mx.uacj.juegora.modelos.Boton
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.Pista

object RepositorioPruebas{
    var pistas = listOf(Pista(
        nombre = "pista 1", // Atrasito del edificio C
        ubicacion = Location("proveedor").apply {
            latitude = 31.7424889
            longitude = -106.4320521
        },
        cuerpo = Informacion(
            texto = "Prueba de texto para comprobar esto pista 1",
            imagen = null
        )
    ),
        Pista(
            nombre = "pista 2", // Cercas del edificio H
            ubicacion = Location("proveedor").apply {
                latitude = 31.742644
                longitude = -106.4313187
            },
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 2",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 3", // Atras del edificio Z
            ubicacion = Location("proveedor").apply {
                latitude = 31.744064
                longitude = -106.4331397
            },
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 3",
                imagen = null
            )
        ),

        Pista(
            nombre = "pista_4",
            ubicacion = Location("proveedor"),
            distanciaMaxima = 15.0f,
            cuerpo = InformacionInteractiva(
                texto = "Esto es una prueba de pista tipo interactiva",
                listaBotones = listOf(
                    Boton(
                        texto = "Ir a pantalla 1",
                        direccion = null
                    ),
                    Boton(
                        texto = "Ir a pantalla dos",
                        direccion = null
                        )
                    )
                )
            ),

        )
    }