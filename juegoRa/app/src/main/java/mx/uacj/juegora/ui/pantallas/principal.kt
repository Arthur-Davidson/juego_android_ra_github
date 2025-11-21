package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
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
import mx.uacj.juegora.viewModels.ControladorGeneral
import mx.uacj.juegora.viewModels.GestorUbicacion

@Composable
fun Principal(
    navegador: NavHostController,
    modificador: Modifier = Modifier,
    gestor_ubicacion: GestorUbicacion = hiltViewModel(),
    controlador_general: ControladorGeneral = hiltViewModel()
){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC1E6F5))
    ) {

        var mostrar_pantalla_generica by remember { mutableStateOf(true) }
        var mostrar_pista_cercana by remember { mutableStateOf(false) }
        var mostrar_informacion_relacionada_con_las_agitadas by remember { mutableStateOf(false) }

        var ubicacion = gestor_ubicacion.ubicacion_actual

        // Valor de LPM visible en la barra superior
        var nivel_de_distancia by remember { mutableStateOf(0f) }

        // BARRA SUPERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .background(Color(0xFF200878))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "LPM: ${nivel_de_distancia.toInt() + 60}",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Ubicación actual: ${ubicacion.value}",
                    color = Color.White,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // CONTENIDO PRINCIPAL
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            for(pista in RepositorioPruebas.pistas){

                if(ubicacion.value == null){
                    break
                }

                // Se calcula aquí (adentro del for)
                val distancia_a_la_pista = ubicacion.value!!.distanceTo(pista.ubicacion)

                if(distancia_a_la_pista < pista.distancia_maxima){

                    mostrar_pantalla_generica = false

                    // Actualiza Latidos por minuto real
                    nivel_de_distancia = (distancia_a_la_pista * 100) /
                            (pista.distancia_maxima - pista.distancia_minima)

                    // ----------- CONTENEDOR DE PISTAS ----------- //
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF074D66),shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                            .padding(12.dp)
                            .padding(bottom = 10.dp)

                    ) {

                        Text("La distancia a la pista es: ${distancia_a_la_pista}", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                        Text("La pista es: ${pista.nombre}", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                        Text("el nivel de la distancia a la pista es ${nivel_de_distancia}", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)

                        if(nivel_de_distancia > 75){
                            Text("Estas frio todavia", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                        }
                        else if (nivel_de_distancia > 50){
                            Text("Te estas acercando", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                        }
                        else if(nivel_de_distancia > 25){
                            Text("Muy cercas, sigue asi", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                        }
                        else if(distancia_a_la_pista < pista.distancia_minima) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFF074D66),shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                                    .padding(12.dp)
                                    .padding(bottom = 10.dp)
                                    .clickable {
                                        navegador.navigate("SelectorPantallaPista")
                                        controlador_general.seleccionar_pista(pista)
                                    }
                            ){
                                Text("Capturar pista cercana", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                            }
                        }

                    } // FIN CONTENEDOR PISTA

                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

            if(mostrar_pantalla_generica){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF7C1004),shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                        .padding(12.dp)
                        .padding(bottom = 10.dp)

                ){
                    Text("No te encuentras cercas de alguna pista por el momento ", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                    Text("Por favor sigue explorando", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                }
            }
        }

        // BARRA INFERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF200878))
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ) {

            Column (horizontalAlignment = Alignment.CenterHorizontally) {

                DetectorAgitamiento(
                    meta_de_agitadas = 20,
                    al_llegar_a_la_meta = {
                        mostrar_informacion_relacionada_con_las_agitadas = true
                    }
                )

                if(mostrar_informacion_relacionada_con_las_agitadas){
                    Text(
                        "Ya me sarandeastes demasiado",
                        color = Color.White,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}





