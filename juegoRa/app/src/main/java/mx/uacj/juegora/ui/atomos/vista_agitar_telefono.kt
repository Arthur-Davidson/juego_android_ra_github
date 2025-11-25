package mx.uacj.juegora.ui.atomos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.uacj.juegora.R
import mx.uacj.juegora.modelos.InformacionAgitar
import mx.uacj.juegora.ui.organismos.DetectorAgitamiento


//RAUL PRUEBA EL CODIGO, ESTE ES PARA REVELAR EL TEXTO, CHECA SI FUNCIONA
@Composable
fun VistaAgitarTelefono(info: InformacionAgitar) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC1E6F5.toLong()))
    ) {
        // BARRA SUPERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF0C2345.toLong()))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Pista 3:",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Por el edificio D",
                    color = Color.White,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

            }
        }

        // CONTENIDO PRINCIPAL
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            var shakeCount by remember { mutableStateOf(0) }

            // Conectar sensor real
            DetectorAgitamiento(
                meta_de_agitadas = info.meta2, // o info.meta1 si quieres un límite distinto
                al_llegar_a_la_meta = {
                    shakeCount = info.meta2 // marca que llegó a la meta
                }
            )

            val mensaje = when {
                shakeCount < info.meta1 -> "** ****** ***** ** ********* ****** ** ******* ****"
                shakeCount in info.meta1 until info.meta2 -> "*a *u*rt* p#*t# #a *nc*n*r#r#s *#*pu*s *e re**r#ar *d#o"
                else -> info.textoOculto
            }

            Spacer(Modifier.height(150.dp))
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF074D66.toLong()), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                    .padding(20.dp)
                    .padding(bottom = 10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Agita tu teléfono para decifrar",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "el mensaje oculto",
                    style = MaterialTheme.typography.titleLarge,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(Modifier.height(20.dp))


                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFD4FFF4.toLong()), shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp))
                        .padding(10.dp),
                    contentAlignment = Alignment.Center
                ){
                    Text(
                        text = mensaje,
                        style = MaterialTheme.typography.bodyLarge,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }


                Spacer(Modifier.height(20.dp))


                /// QUITAR
                Text(text = "Agitadas: $shakeCount")

                // Botón de simulación opcional (para pruebas) quitalo si ves que funciona el agitamiento del telefono
                Button(onClick = { shakeCount++ }) {
                    Text("Simular Agitada")
                }
            }
        }

        // BARRA INFERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFF0C2345.toLong()))
                .padding(15.dp),
        )

    }
}

