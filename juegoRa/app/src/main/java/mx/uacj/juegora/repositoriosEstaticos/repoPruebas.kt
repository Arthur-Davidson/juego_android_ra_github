package mx.uacj.juegora.repositoriosEstaticos

import android.location.Location
import androidx.collection.emptyObjectList
import androidx.collection.objectListOf
import mx.uacj.juegora.modelos.Boton
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.Pista

object RepositorioPruebas{
    var pistas = listOf(Pista(
        nombre = "pista1",
        ubicacion = Location("prooveedor").apply{
            latitude = 31.7170974
                longitude = -106.4251485
        },
        cuerpo = Informacion(
            texto = "Prueba de texto para comprobar esto 1",
            image = null
        )
    ),
        Pista(
            nombre = "pista2",
            ubicacion = Location("prooveedor").apply {
                latitude = 31.7422298
                    longitude = -106.4321442
            },
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto 2",
                image = null
            )
        ),
        Pista(
            nombre = "pista3",
            ubicacion = Location("prooveedor"),
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto 3",
                image = null
            )
        ),
        Pista(
            nombre = "pista4",
            ubicacion = Location("prooveedor"),
            cuerpo = InformacionInteractiva(
                texto = "Prueba de pista tipo interactiva",
                listaBotones = listOf(
                    Boton(
                        texto = "Ir a pantalla 1",
                        direccion = null
                    ),
                    Boton(
                        texto = "Ir a pantalla 2",
                        direccion = null
                    )
                )
            )
        ),
    )
}