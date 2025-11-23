package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
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
import mx.uacj.juegora.repositoriosEstaticos.estaticos.RepositorioPruebas
import mx.uacj.juegora.ui.moleculas.PantallaBienvenida
import mx.uacj.juegora.ui.organismos.DetectorAgitamiento
import mx.uacj.juegora.viewModels.ControladorGeneral
import mx.uacj.juegora.viewModels.GestorUbicacion

@Composable
fun Principal(
    navegador: NavHostController,
    modificador: Modifier = Modifier,
    gestor_ubicacion: GestorUbicacion = hiltViewModel(),
    controlador_general: ControladorGeneral = hiltViewModel()
){
    // Pantalla emergente de bienvenida a la Aplicacion
    var mostrarBienvenida by remember { mutableStateOf(!controlador_general.bienvenidaMostrada.value) }

    if (mostrarBienvenida) {
        PantallaBienvenida {
            mostrarBienvenida = false
            controlador_general.bienvenidaMostrada.value = true
        }
    }

    //Resto de la aplicacion
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC1E6F5))
    ) {

        var mostrar_pantalla_generica by remember { mutableStateOf(true) }
        var mostrar_informacion_relacionada_con_las_agitadas by remember { mutableStateOf(false) }
        var ubicacion = gestor_ubicacion.ubicacion_actual
        var nivel_de_distancia by remember { mutableStateOf(0f) } // Valor de BPM visible en la barra superior

        // BARRA SUPERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .background(Color(0xFF0C2345))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "BPM: ${nivel_de_distancia.toInt() + 60}",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                //Estoy ocultando esto para que no se vea sucio el header
                /*
                Text(
                    text = "Ubicación actual:",
                    color = Color(0xFF7DDCFF),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "${ubicacion.value}",
                    color = Color.White,
                    fontSize = 15.sp,
                )*/
            }
        }

        // CONTENIDO PRINCIPAL
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {

            for(pista in RepositorioPruebas.pistas){

                if(ubicacion.value == null){
                    break
                }

                val distancia_a_la_pista = ubicacion.value!!.distanceTo(pista.ubicacion)

                if(pista.id in 1..4 && distancia_a_la_pista < pista.distancia_maxima){
                    mostrar_pantalla_generica = false

                    nivel_de_distancia = (distancia_a_la_pista * 100) / (pista.distancia_maxima - pista.distancia_minima)

                    if(nivel_de_distancia < 70){
                        // ----------- CONTENEDOR DE PISTAS 1-4 ----------- //
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF074D66), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                                .padding(12.dp)
                                .padding(bottom = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text("La pista es:", color = Color(0xFFD4FFF4), fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            Text("${pista.nombre}", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold)

                            Spacer(modifier = Modifier.height(8.dp))

                            Text("La distancia a la pista es: ${distancia_a_la_pista} metros", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                            Text("El nivel de la distancia a la pista es ${nivel_de_distancia} metros", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)

                            Spacer(modifier = Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFD4FFF4), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ){
                                if(nivel_de_distancia > 100){
                                    Text("Estas frio todavia", color = Color(0xFF074D66), fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
                                }
                                else if (nivel_de_distancia > 30){
                                    Text("Te estas acercando", color = Color(0xFFA8A202), fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
                                }
                                else if(nivel_de_distancia > 10){
                                    Text("Muy cercas, sigue asi", color = Color(0xFF02A826), fontWeight = FontWeight.Bold, modifier = Modifier.padding(12.dp))
                                }
                                else if(distancia_a_la_pista < pista.distancia_minima){
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(Color(0xFF94BDFF), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                                            .padding(12.dp)
                                            .clickable {
                                                controlador_general.seleccionar_pista(pista)
                                                pista.completada = true
                                                navegador.navigate("SelectorPantallaPista")
                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ){
                                        // --- Estado de la pista agregado ---
                                        Text(
                                            text = "Estado: ${if (pista.completada) "Completada ✅" else "Pendiente ❌"}",
                                            fontWeight = FontWeight.Bold,
                                            color = Color.White,
                                            modifier = Modifier.padding(bottom = 8.dp)
                                        )
                                        Text("Capturar pista cercana", color = Color.White, fontWeight = FontWeight.Bold)
                                    }
                                }
                            }
                        } // FIN CONTENEDOR PISTAS 1-4

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
                else if(pista.id == 5){
                    val pistasIniciales = RepositorioPruebas.pistas.filter { it.id in 1..4 }
                    val todasCompletadas = pistasIniciales.all { it.completada }

                    if(todasCompletadas && distancia_a_la_pista < pista.distancia_maxima){
                        mostrar_pantalla_generica = false

                        // ----------- CONTENEDOR DE PISTA 5 ----------- //
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color(0xFF074D66), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                                .padding(12.dp)
                                .padding(bottom = 10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ){

                            Text("${pista.nombre}", color = Color.White, fontSize = 19.sp, fontWeight = FontWeight.Bold)
                            Spacer(modifier = Modifier.height(8.dp))

                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFD4FFF4), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)),
                                contentAlignment = Alignment.Center
                            ){
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color(0xFF94BDFF), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                                        .padding(12.dp)
                                        .clickable {
                                            controlador_general.seleccionar_pista(pista)
                                            pista.completada = true
                                            navegador.navigate("SelectorPantallaPista")
                                        },
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    // --- Estado de la pista ---
                                    Text(
                                        text = "Estado: ${if (pista.completada) "Completada ✅" else "Pendiente ❌"}",
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )

                                    Text("Capturar pista cercana", color = Color.White, fontWeight = FontWeight.Bold)
                                }
                            }
                        } // FIN CONTENEDOR PISTA 5

                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

            if(mostrar_pantalla_generica){
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFF7C1004), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                        .padding(12.dp)
                        .padding(bottom = 10.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ){
                    Text("No te encuentras cercas de alguna pista por el momento ", color = Color(0xFF7A0B0BL), fontWeight = FontWeight.Bold)
                    Text("Por favor sigue explorando", color = Color(0xFF7DDCFF), fontWeight = FontWeight.Bold)
                }
            }
        }

        // BARRA INFERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF0C2345))
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ){

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

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
