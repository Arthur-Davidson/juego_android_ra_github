package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
) {

    var mostrar_pantalla_generica by remember { mutableStateOf(true) }
    var mostrar_pista_cercana by remember { mutableStateOf(false) }
    var mostrar_informacion_relacionada_con_las_agitadas by remember { mutableStateOf(false) }

    val ubicacion = gestor_ubicacion.ubicacion_actual

    val fondoPantalla = Color(0xFFC1E6F5)
    val colorTarjeta = Color(0xFF074D66)
    val colorTexto = Color(0xFFC1E6F5)
    val barraColor = Color(0xFF200878)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(fondoPantalla)
    ) {

        // Barra superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(barraColor)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp)
        ) {

            DetectorAgitamiento(
                meta_de_agitadas = 20,
                al_llegar_a_la_meta = {
                    mostrar_informacion_relacionada_con_las_agitadas = true
                }
            )

            if (mostrar_informacion_relacionada_con_las_agitadas) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                    modifier = Modifier.fillMaxWidth(),
                    shape = CardDefaults.shape
                ) {
                    Column(
                        Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            "Ya me sarandeastes demasiado",
                            color = colorTexto
                        )
                    }
                }
            }

            for (pista in RepositorioPruebas.pistas) {

                val loc = ubicacion.value ?: break

                val distancia = loc.distanceTo(pista.ubicacion)

                if (distancia < pista.distancia_maxima) {
                    mostrar_pantalla_generica = false

                    val nivel = (distancia * 100) /
                            (pista.distancia_maxima - pista.distancia_minima)

                    Card(
                        colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        shape = CardDefaults.shape
                    ) {

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Text("La pista es: ${pista.nombre}", color = colorTexto)
                            Text("Nivel de distancia: $nivel", color = colorTexto)

                            when {
                                nivel > 75 -> Text("Estas frio todavia", color = colorTexto)
                                nivel > 50 -> Text("Te estas acercando", color = colorTexto)
                                nivel > 25 -> Text("Muy cercas, sigue asi", color = colorTexto)
                            }

                            if (distancia < pista.distancia_minima) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            navegador.navigate("SelectorPantallaPista")
                                            controlador_general.seleccionar_pista(pista)
                                        },
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text("Capturar pista cercana", color = colorTexto)
                                }
                            }
                        }
                    }
                }
            }

            if (mostrar_pantalla_generica) {
                Card(
                    colors = CardDefaults.cardColors(containerColor = colorTarjeta),
                    modifier = Modifier.fillMaxWidth(),
                    shape = CardDefaults.shape
                ) {
                    Column(
                        modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("No te encuentras cerca de alguna pista por el momento", color = colorTexto)
                        Text("Por favor sigue explorando", color = colorTexto)
                    }
                }
            }
        }

        // Barra inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(40.dp)
                .background(barraColor)
        )
    }
}
