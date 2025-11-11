package mx.uacj.juegora.ui.pantallas

import android.location.Location
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.uacj.juegora.modelos.Informacion
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.modelos.TiposDePistas
import mx.uacj.juegora.repositoriosEstaticos.estaticos.estaticos.RepositorioPruebas
import mx.uacj.juegora.ui.organismos.InformacionInteractivaVista
import mx.uacj.juegora.ui.organismos.InformacionVista

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier) {

    var mostrarPantallaGenerica by remember { mutableStateOf(true) }
    var mostrarPistaCercana by remember { mutableStateOf(false) }

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

        // Contenido principal centrado
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            var pistaEncontrada = false

            for (pista in RepositorioPruebas.pistas) {
                if (ubicacion == null) break

                val distanciaAPista = ubicacion.distanceTo(pista.ubicacion)

                if (distanciaAPista < pista.distanciaMaxima) {
                    mostrarPantallaGenerica = false
                    pistaEncontrada = true
                    val nivelDeDistancia =
                        (distanciaAPista * 100) / (pista.distanciaMaxima - pista.distanciaMinima)

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
                                .padding(16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "La pista es: ${pista.nombre}",
                                color = Color(0xFFC1E6F5),
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp
                            )
                            Text(
                                text = "Nivel de distancia: ${nivelDeDistancia.toInt()}",
                                color = Color(0xFFC1E6F5),
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            when {
                                nivelDeDistancia > 75 -> Text(
                                    "Estás frío todavía",
                                    color = Color(0xFFC1E6F5),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                nivelDeDistancia > 50 -> Text(
                                    "Te estás acercando",
                                    color = Color(0xFFC1E6F5),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                nivelDeDistancia > 25 -> Text(
                                    "Muy cerca, sigue así",
                                    color = Color(0xFFC1E6F5),
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 16.sp
                                )

                                nivelDeDistancia < 20 && !mostrarPistaCercana -> {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable { mostrarPistaCercana = true },
                                        horizontalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = "Capturar pista cercana",
                                            color = Color(0xFFC1E6F5),
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 16.sp
                                        )
                                    }
                                }
                            }
                        }
                    }

                    if (mostrarPistaCercana) {
                        when (pista.cuerpo.tipo) {
                            TiposDePistas.texto -> {
                                InformacionVista(pista.cuerpo as Informacion)
                            }

                            TiposDePistas.interactiva -> {
                                InformacionInteractivaVista(pista.cuerpo as InformacionInteractiva)
                            }

                            TiposDePistas.camara -> TODO()
                            TiposDePistas.agitarTelefono -> TODO()
                        }
                    }
                }
            }

            // 🎨 Pantalla genérica cuando no hay pistas
            if (mostrarPantallaGenerica || !pistaEncontrada) {
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
        }

        // 🎨 Barra inferior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .background(Color(0xFF200878))
        )
    }
}
