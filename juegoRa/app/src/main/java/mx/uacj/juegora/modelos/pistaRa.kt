package mx.uacj.juegora.modelos

import android.location.Location

data class Pista(
    var ubicacion: Location,
    var cuerpo: PistaGenerica,
    var distanciaMaxima: Float,
    var nombre: String
)

