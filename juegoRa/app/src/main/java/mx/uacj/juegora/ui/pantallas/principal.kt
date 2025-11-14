package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.TiposDePistas
import mx.uacj.juegora.repositoriosEstaticos.estaticos.RepositorioPruebas
import mx.uacj.juegora.ui.organismos.DetectorAgitamiento
import mx.uacj.juegora.ui.organismos.InformacionInteractivaVista
import mx.uacj.juegora.ui.organismos.InformacionVista
import mx.uacj.juegora.viewModels.GestorUbicacion

@Composable
fun Principal(navegador: NavHostController modificador: Modifier = Modifier, gestor_ubicacion: GestorUbicacion = hiltViewModel()){

    var mostrar_pantalla_generica by remember { mutableStateOf(true) }
    var mostrar_pista_cercana by remember { mutableStateOf(false) }

    var mostrar_informacion_relacionada_con_las_agitadas by remember { mutableStateOf(false) }

    var ubicacion = gestor_ubicacion.ubicacion_actual

    Column(
        modifier = modificador
            .fillMaxSize()
            .background(Color(0xFFC1E6F5)), // Fondo
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Barra superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(Color(0xFF200878))
        )

        DetectorAgitamiento(meta_de_agitadas = 20, al_llegar_a_la_meta = {
            mostrar_informacion_relacionada_con_las_agitadas = true
        })
        if(mostrar_informacion_relacionada_con_las_agitadas){
            Text("Ya me agitaste demasiado")
        }
        Text("Ubicacion actual: ${ubicacion.value}")
        for(pista in RepositorioPruebas.pistas){
            if(ubicacion.value == null){
                break
            }

            var distancia_a_la_pista = ubicacion.value.distanceTo(pista.ubicacion)
            Text("La distancia a la pista es: ${distancia_a_la_pista}")

            if(distancia_a_la_pista < pista.distancia_maxima){
                mostrar_pantalla_generica = false
                var nivel_de_distancia = (distancia_a_la_pista * 100) / (pista.distancia_maxima - pista.distancia_minima)

                Text("La pista es: ${pista.nombre}")
                Text("el nivel de la distancia a la pista es ${nivel_de_distancia}")

                if(nivel_de_distancia > 75){
                    Text("Estas frio todavia")
                }

                else if (nivel_de_distancia > 50){
                    Text("Te estas acercando")
                }

                else if(nivel_de_distancia > 25){
                    Text("Muy cercas, sigue asi")
                }

                else if(nivel_de_distancia < 20 && !mostrar_pista_cercana){
                    Row(modifier = Modifier.fillMaxWidth().clickable {
                        mostrar_pista_cercana = true
                    }){
                        Text("Capturar pista cercana")
                    }
                }

                else if (distancia_a_la_pista < pista.distancia_minima) {

                }


            }
        }
    }

    if(mostrar_pantalla_generica){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFF074D66)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No te encuentras cerca de alguna pista por el momento",
                    color = Color(0xFFC1E6F5),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
                Text(
                    text = "Por favor sigue explorando",
                    color = Color(0xFFC1E6F5),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp
                )
            }
        }

    }

    // Barra inferior
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .background(Color(0xFF200878))
    )

}

