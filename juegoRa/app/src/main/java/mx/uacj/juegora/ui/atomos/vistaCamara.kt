package mx.uacj.juegora.ui.atomos

import androidx.camera.compose.CameraXViewfinder
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.uacj.juegora.R
import mx.uacj.juegora.viewModels.servicios.ServicioCamara

@Composable
fun VistaCamara(
    servicio_de_camara: ServicioCamara = ServicioCamara(),
    modificador: Modifier = Modifier,
    dueño_del_ciclo_de_vida: LifecycleOwner = LocalLifecycleOwner.current
) {
    val contexto = LocalContext.current

    // Conexión con la cámara
    LaunchedEffect(Unit) {
        servicio_de_camara.conectar_con_camara(contexto.applicationContext, dueño_del_ciclo_de_vida)
    }

    Box(modificador.fillMaxSize()) {

        // Usamos AndroidView para PreviewView y Preview directamente
        androidx.compose.ui.viewinterop.AndroidView(
            factory = { ctx ->
                val previewView = androidx.camera.view.PreviewView(ctx)
                servicio_de_camara.providePreviewView(previewView) // metodo que creamos en el servicio
                previewView
            },
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.tieso),
            contentDescription = null,
            modifier = Modifier
                .height(250.dp) // Altura deseada
                .fillMaxWidth() // Ancho igual al de la pantalla
                .align(androidx.compose.ui.Alignment.BottomCenter) // Posición en la parte inferior
                .graphicsLayer(alpha = 0.85f),
            contentScale = ContentScale.Fit
        )
    }
}
