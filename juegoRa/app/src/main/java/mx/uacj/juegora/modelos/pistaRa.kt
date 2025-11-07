package mx.uacj.juegora.modelos

import android.location.Location

data class Pista(
    var nombre: String,
    var ubicacion: Location,
    var distanciaMinima: Float = 15F,
    var distanciaMaxima: Float = 150F,
    var cuerpo: PistaGenerica
)


