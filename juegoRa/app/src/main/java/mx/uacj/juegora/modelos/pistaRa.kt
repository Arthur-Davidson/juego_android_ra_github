package mx.uacj.juegora.modelos

import android.location.Location

data class Pista(
    val id: Int, // nuevo
    val nombre: String,
    val ubicacion: Location,
    val cuerpo: PistaGenerica,
    var completada: Boolean = false, // nueva propiedad
    val distancia_minima: Float = 15f,
    val distancia_maxima: Float = 150f
)



