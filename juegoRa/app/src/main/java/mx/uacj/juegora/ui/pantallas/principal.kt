package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.TiposDePistas
import mx.uacj.juegora.repositoriosEstaticos.RepositorioPruebas
import mx.uacj.juegora.ui.organismos.InformacionInteractivaVista
import mx.uacj.juegora.ui.organismos.InformacionVista

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier){

    var mostrarPantallaGenerica by remember { mutableStateOf(true) }
    var mostrarPistaCercana by remember { mutableStateOf(false) }

    Column(modificador) {
        for(pista in RepositorioPruebas.pistas){
            if(ubicacion == null){
                break
            }

            var distanciaAPista = ubicacion.distanceTo(pista.ubicacion)

            if(distanciaAPista < pista.distanciaMaxima){
                mostrarPantallaGenerica = false
                var nivelDeDistancia = (distanciaAPista * 100) / (pista.distanciaMaxima - pista.distanciaMinima)

                Text("La pista es: ${pista.nombre}")
                Text("el nivel de la distancia a la pista es ${nivelDeDistancia}")

                if(nivelDeDistancia > 75){
                    Text("Estas frio todavia")
                }

                else if (nivelDeDistancia > 50){
                    Text("Te estas acercando")
                }

                else if(nivelDeDistancia > 25){
                    Text("Muy cercas, sigue asi")
                }

                else if(nivelDeDistancia < 20 && !mostrarPistaCercana){
                    Row(modifier = Modifier.fillMaxWidth().clickable {
                        mostrarPistaCercana = true
                    }){
                        Text("Capturar pista cercana")
                    }
                }

                if(mostrarPistaCercana) {
                    when (pista.cuerpo.tipo) {
                        TiposDePistas.texto -> {
                            InformacionVista(pista.cuerpo as Informacion)
                        }

                        TiposDePistas.interactiva -> {
                            InformacionInteractivaVista(pista.cuerpo as InformacionInteractiva)
                        }

                        TiposDePistas.camara -> {
                            TODO()
                        }

                        TiposDePistas.agitarTelefono -> {
                            TODO()
                        }
                    }
                }

            }
        }
    }

    if(mostrarPantallaGenerica){
        Column(modificador) {
            Text("No te encuentras cercas de alguna pista por el momento ")
            Text("Por favor sigue explorando  ")
        }

    }

}
