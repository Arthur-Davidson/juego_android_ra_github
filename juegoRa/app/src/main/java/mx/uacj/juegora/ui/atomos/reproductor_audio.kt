package mx.uacj.juegora.ui.atomos

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import mx.uacj.juegora.viewModels.servicios.ReproductorAudio
import mx.uacj.juegora.R

@Composable
fun VistaReproductorAudio() {
    val contexto = LocalContext.current

    val reproductor_de_audio = remember { ReproductorAudio(contexto, loopear = true) }
    var se_esta_escuchando by remember { mutableStateOf(false) }

    DisposableEffect(Unit) {
        onDispose {
            reproductor_de_audio.detener()
        }
    }

/* //ESTE LAUNCEHD EFFECT HACE QUE SE REPRODUZCA EL AUDIO AL INICIO
    LaunchedEffect(reproductor_de_audio) {
        reproductor_de_audio.cargar_audio(R.raw.audio_test) //audio_test es el archivo de audio que esta en la carpeta raw y este debemos de sustituir para colocar el nuestro
    }*/

    Button(onClick = {
        if (se_esta_escuchando){
            reproductor_de_audio.detener()
        } else {
            reproductor_de_audio.cargar_audio(R.raw.llamada)
        }
        se_esta_escuchando = !se_esta_escuchando
    }) {
        Text(text = if (se_esta_escuchando) "Pausar Audio" else "Reproducir Audio")
    }
}