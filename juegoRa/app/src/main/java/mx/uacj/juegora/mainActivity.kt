package mx.uacj.juegora

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import android.util.Pair
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
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
                    var textoUbicacion by remember { mutableStateOf("No tengo permisos para ver tu ubicacion") }
                    var mostrarResultadoPermisos by remember { mutableStateOf(false) }
                    var textoPermisosObtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    var  ultimaUbicacionConocida by remember { mutableStateOf<Location?>(null) }

                    ParaLaSolicitudDePermisos (
                        conPermisosObtenidos = {
                            mostrarResultadoPermisos = true

                            obtenerUbicacionUsuario(
                                cuando_obtenga_la_ultima_psoicion_corecta = { ubicacion ->
                                    Log.v("UBICACION", "${ubicacion.first}")
                                    Log.v("UBICACION", "${ubicacion.second}")
                                    /*textoUbicacion = "Tu ubicacion es latitud: ${ubicacion.first} longitud: ${ubicacion.second}"*/
                                    val ubicacionActual = Location("Sistema de ubicacion")
                                    ubicacionActual.latitude = ubicacion.first
                                    ubicacionActual.longitude = ubicacion.second

                                    ultimaUbicacionConocida = ubicacionActual
                                },
                                cuando_falle_al_obtener_ubicacion = { errorEncontrado ->
                                    textoUbicacion = "Error: ${errorEncontrado.localizedMessage}"
                                },
                                cuando_la_ultima_posicion_sea_nula = {
                                    textoUbicacion = "Error: la ubicacion es nula"
                                }
                            )
                        },
                        sinPermisosObtenidos = {
                            mostrarResultadoPermisos = true
                            textoPermisosObtenidos =
                                "No tengo los permisos necesarios para funcionar"
                        }
                    ) {}

                    /*Text(textoUbicacion, Modifier.padding(innerPadding))*/

                    Principal (
                        modificador = Modifier.padding(innerPadding),
                        ubicacion = ultimaUbicacionConocida
                        )

                }
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun obtenerUbicacionUsuario(
        cuando_obtenga_la_ultima_psoicion_corecta: (Pair<Double, Double>) -> Unit,
        cuando_falle_al_obtener_ubicacion: (Exception) -> Unit,
        cuando_la_ultima_posicion_sea_nula: () -> Unit
    ){
        conexionParaObtenerPermisos = LocationServices.getFusedLocationProviderClient(this)

        if(tenemos_los_permisos_de_ubicacion()){
            conexionParaObtenerPermisos.lastLocation
                .addOnSuccessListener {ubicacion ->
                    if(ubicacion != null){
                        cuando_obtenga_la_ultima_psoicion_corecta(Pair(ubicacion.latitude, ubicacion.longitude))
                    }
                    else {
                        cuando_la_ultima_posicion_sea_nula()
                    }
                }
                .addOnFailureListener{ error ->
                    cuando_falle_al_obtener_ubicacion(error)
                }
        }
    }

    @SuppressLint("MissingPermission")
    fun obtener_ubicacion(
        al_obtener_la_ubicacion: (Pair<Double, Double>) -> Unit,
        al_obtener_un_error: (Exception) -> Unit,
        prioridad: Boolean = true
    ){
        val precision = if(prioridad) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if(tenemos_los_permisos_de_ubicacion()){
            conexionParaObtenerPermisos.getCurrentLocation(
                precision, CancellationTokenSource().token
            ).addOnSuccessListener { ubicacion ->
                if(ubicacion != null){
                    al_obtener_la_ubicacion(Pair(ubicacion.latitude, ubicacion.longitude))
                }
            }
                .addOnFailureListener{ error ->
                    al_obtener_un_error(error)
                }
        }

    }

    private fun tenemos_los_permisos_de_ubicacion(): Boolean{
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
                )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JuegoRATheme {

    }
}