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
                latitude = 31.74211709842721
                longitude = -106.43233442043234
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
                latitude = 31.7424
                longitude = -106.4319
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
                latitude = 31.742644
                longitude = -106.4313187
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
                latitude = 31.74301171690772
                longitude = -106.43250608737333
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
                latitude = 31.74211709842721
                longitude = -106.43233442043234
            },
            cuerpo = Informacion(
                texto = "Tú eres el asesino....",
                imagen = R.drawable.acosador_2
            ),
            completada = false
        )
    )
}

