package mx.uacj.juegora.ui.atomos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import mx.uacj.juegora.modelos.InformacionAgitar
import mx.uacj.juegora.ui.organismos.DetectorAgitamiento


//RAUL PRUEBA EL CODIGO, ESTE ES PARA REVELAR EL TEXTO, CHECA SI FUNCIONA
@Composable
fun VistaAgitarTelefono(info: InformacionAgitar) {
    var shakeCount by remember { mutableStateOf(0) }

    // Conectar sensor real
    DetectorAgitamiento(
        meta_de_agitadas = info.meta2, // o info.meta1 si quieres un límite distinto
        al_llegar_a_la_meta = {
            shakeCount = info.meta2 // marca que llegó a la meta
        }
    )

    val mensaje = when {
        shakeCount < info.meta1 -> "**** *** ***"
        shakeCount in info.meta1 until info.meta2 -> "h*l* q** t**"
        else -> info.textoOculto
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Agita tu teléfono",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(10.dp))

        Text(
            text = mensaje,
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(Modifier.height(20.dp))
        Text(text = "Agitadas: $shakeCount")

        // Botón de simulación opcional (para pruebas) quitalo si ves que funciona el agitamiento del telefono
        Button(onClick = { shakeCount++ }) {
            Text("Simular Agitada")
        }
    }
}

