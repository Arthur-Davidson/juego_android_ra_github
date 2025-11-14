package mx.uacj.juegora.ui.atomos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import mx.uacj.juegora.viewModels.servicios.ServicioCamara

@Composable
fun VistaCamara(
    servicio_de_camara: ServicioCamara = ServicioCamara(),
    modificador: Modifier = Modifier,
    dueno_del_ciclo_de_vida: LifecycleOwner = LocalLifecycleOwner.current
){
    val superficie_del_dibujado by servicio_de_camara.surface_vista_camara.collectAsStateWithLifecycle()
    val contexto = LocalContext.current

    LaunchedEffect(dueno_del_ciclo_de_vida) {
        servicio_de_camara.conectar_con_camara(contexto.applicationContext, dueno_del_ciclo_de_vida)
    }

    superficie_del_dibujado?.let { peticion ->
        CameraXViewfinder(
            surfaceRequest = peticion,
            modificador
        )
    }
}