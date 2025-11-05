package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.TiposPistas
import mx.uacj.juegora.repositoriosEstaticos.RepositorioPruebas
import mx.uacj.juegora.ui.organismos.InformacionInteractivaVista
import mx.uacj.juegora.ui.organismos.InformacionVista
import java.security.Principal

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier) {
    /*val miUbicacion = Location("proveedor").apply {
        latitude = 31.7422298
        longitude = -106.4321442
    }*/
    Column(modificador) {
        for (pista in RepositorioPruebas.pistas){
            Text("La pista es: ${pista.nombre}")
            Text("La distancia a la pista es ${ubicacion?.distanceTo(pista.ubicacion)}")

            if(ubicacion == null){
                continue
            }

            if(ubicacion.distanceTo(pista.ubicacion) < pista.distanciaMaxima){
                when(pista.cuerpo.tipo){
                    TiposPistas.texto -> {
                        InformacionVista(pista.cuerpo as Informacion)
                    }
                    TiposPistas.interactiva -> {
                        InformacionInteractivaVista(pista.cuerpo as InformacionInteractiva)
                    }
                    TiposPistas.camara -> {
                        TODO()
                    }
                    TiposPistas.agitarTelefono -> {
                        TODO()
                    }
                }

            }

            Text("---------")
            if(pista.cuerpo.tipo == TiposPistas.texto){
                InformacionVista(pista.cuerpo as Informacion)
            }
        }
    }

}