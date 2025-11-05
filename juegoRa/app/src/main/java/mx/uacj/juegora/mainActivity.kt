package mx.uacj.juegora

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import mx.uacj.juegora.gestorPermisos.ParaLaSolicitudDePermisos
import mx.uacj.juegora.ui.pantallas.Principal
import mx.uacj.juegora.ui.theme.JuegoRATheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var conexionParaObtenerPermisos: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuegoRATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var textoUbicacion by remember { mutableStateOf("NO tengo permisos para ver tu ubicacion") }
                    var mostrarResultadoPermisos by remember { mutableStateOf(false) }
                    var textoPermisosObtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    ParaLaSolicitudDePermisos(
                        conPermisosObtenidos = {
                            mostrarResultadoPermisos = true
                            textoPermisosObtenidos =
                        }
                    ) { }

                    Principal(Modifier.padding(innerPadding))
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JuegoRATheme {

    }
}