package mx.uacj.juegora.repositoriosEstaticos.estaticos

import android.location.Location
import androidx.collection.objectListOf
import mx.uacj.juegora.R
import mx.uacj.juegora.modelos.Boton
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionAgitar
import mx.uacj.juegora.modelos.InformacionAudio
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.Pista
import mx.uacj.juegora.modelos.TiposDePistas

object RepositorioPruebas {
    var pistas = listOf(
        Pista(
            id = 1,
            nombre = "¡Donde todo comienza...!",
            ubicacion = Location("proveedor").apply {
                latitude = 31.620299889363597
                longitude = -106.38162871344515
            },
            cuerpo = InformacionAudio(
                texto = "Escuchas un mensaje extraño... Tal vez debas comenzar la investigación dentro del campus."
            ),
            completada = false
        ),

        Pista(
            id = 2,
            nombre = "Atras del edificio C",
            ubicacion = Location("proveedor").apply {
                latitude = 31.620299889363597
                longitude = -106.38162871344515
            },
            cuerpo = Informacion(
                texto = "Nunca estas solo, JAMAS estaras solo",
                imagen = R.drawable.acosador_1
            ),
            completada = false
        ),

        Pista(
            id = 3,
            nombre = "Por el edificio D",
            ubicacion = Location("proveedor").apply {
                latitude = 31.620299889363597
                longitude = -106.38162871344515
            },
            cuerpo = InformacionAgitar(
                textoOculto = "Hola que tal",
                meta1 = 5,
                meta2 = 10
            ),
            completada = false
        ),

        Pista(
            id = 4,
            nombre = "La cafeteria",
            ubicacion = Location("proveedor").apply {
                latitude = 31.620299889363597
                longitude = -106.38162871344515
            },
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 3",
                imagen = null
            ),
            completada = false
        ),

        Pista(
            id = 5,
            nombre = "¡CONOCE LA VERDAD!",
            ubicacion = Location("proveedor").apply {
                latitude = 31.620299889363597
                longitude = -106.38162871344515
            },
            cuerpo = Informacion(
                texto = "Tú eres el asesino....",
                imagen = R.drawable.acosador_2
            ),
            completada = false
        )
    )
}

