package mx.uacj.juegora.ui.organismos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.uacj.juegora.ui.atomos.VistaReproductorAudio

@Composable
fun InformacionAudioVista(texto: String, modificador: Modifier = Modifier) {
    Column(modificador) {
        Text(texto)
        VistaReproductorAudio()
    }
}
