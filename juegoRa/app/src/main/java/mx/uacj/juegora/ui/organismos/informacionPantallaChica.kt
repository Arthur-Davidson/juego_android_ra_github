package mx.uacj.juegora.ui.organismos

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.uacj.juegora.modelos.Informacion

@Composable
fun InformacionVista(informacion_a_mostrar: Informacion){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFC1E6F5.toLong()))
            .verticalScroll(rememberScrollState())
    ) {
        // BARRA SUPERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(Color(0xFF0C2345.toLong()))
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    text = "Pista:",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "No todo es lo que parece",
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        if(informacion_a_mostrar.imagen != null){
            Image(
                painter = painterResource(informacion_a_mostrar.imagen),
                contentDescription = "Imagen de la pista",
                modifier = Modifier
                    //.height(1000.dp)
                    .fillMaxWidth() // Ancho igual al de la pantalla
                    .graphicsLayer(alpha = 0.85f),
                contentScale = ContentScale.Fit
            )
        }
        /*
        informacion_a_mostrar.imagen?.let {
            Image(
                painter = painterResource(informacion_a_mostrar.imagen),
                contentDescription = "Imagen de la pista"
            )
        }
        */

        // BARRA INFERIOR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(Color(0xFF0C2345.toLong()))
                .padding(15.dp),
            contentAlignment = Alignment.Center
        ){
            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                Text(
                    informacion_a_mostrar.texto,
                    color = Color.White,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}