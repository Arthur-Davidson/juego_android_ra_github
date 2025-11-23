package mx.uacj.juegora.ui.moleculas

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun PantallaBienvenida(
    cerrar: () -> Unit
) {

    Dialog(onDismissRequest = { /* Evitamos que se cierre tocando fuera */ }) {

        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth(0.9f)
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = "¡Bienvenid@ a El Eco del Asesino AR!")
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = """
                    Este es un juego de realidad aumentada donde tendrás 
                    que moverte por el campus para encontrar pistas, 
                    y avanzar en la historia.

                    Sigue las instrucciones de cada pista y mantente atento 
                    a tu entorno.
                    """.trimIndent()
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(onClick = { cerrar() }) {
                    Text("Comenzar Juego")
                }
            }
        }
    }
}