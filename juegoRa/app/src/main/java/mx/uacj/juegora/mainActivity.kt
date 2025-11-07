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
import dagger.hilt.android.AndroidEntryPoint
import mx.uacj.juegora.gestor_permisos.ParaLaSolictudDePermisos
import mx.uacj.juegora.ui.pantallas.Principal
import mx.uacj.juegora.ui.theme.JuegoRATheme


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var conexionParaObtenerUbicacion: FusedLocationProviderClient


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JuegoRATheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var textoDeUbicacion by remember { mutableStateOf("No tengo permisos para ver tu ubicacion") }
                    var mostrarResutladoDePermisos by remember { mutableStateOf(false) }
                    var textoPermisosObtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    var ultimaUbicacionConocida by remember { mutableStateOf<Location?>(null) }

                    ParaLaSolictudDePermisos(
                        conPermisosObtenidos = {
                            mostrarResutladoDePermisos = true

                            obtenerUbicacionUsuario(
                                cuandoObtengaLaUltimaPosicionCorrecta = { ubicacion ->
                                    Log.v("UBICACION", "${ubicacion.first}")
                                    Log.v("UBICACION", "${ubicacion.second}")

                                    val ubicacionActual = Location("SistemaDeUbicacion")
                                    ubicacionActual.latitude = ubicacion.first
                                    ubicacionActual.longitude = ubicacion.second

                                    ultimaUbicacionConocida = ubicacionActual
                                },
                                cuandoFalleAlObtenerUbicacion = { errorEncontrado ->
                                    textoDeUbicacion = "Error: ${errorEncontrado.localizedMessage}"
                                },
                                cuandoUltimaPosicionSeaNula = {
                                    textoDeUbicacion = "Error: la ubicacion es nula por alguna razon"
                                }
                            )
                        },
                        sinPermisosObtenidos = {
                            mostrarResutladoDePermisos = true
                            textoPermisosObtenidos =
                                "No tengo los permisos necesarios para funcionar"
                        }
                    ) {}

                    Principal(
                        modificador = Modifier.padding(innerPadding),
                        ubicacion = ultimaUbicacionConocida
                    )

                }
            }
        }
    }


    @SuppressLint("MissingPermission")
    fun obtenerUbicacionUsuario(
        cuandoObtengaLaUltimaPosicionCorrecta: (Pair<Double, Double>) -> Unit,
        cuandoFalleAlObtenerUbicacion: (Exception) -> Unit,
        cuandoUltimaPosicionSeaNula: () -> Unit
    ){
        conexionParaObtenerUbicacion = LocationServices.getFusedLocationProviderClient(this)

        if(tenemosPermisosUbicacion()){
            conexionParaObtenerUbicacion.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token)
                .addOnSuccessListener {ubicacion ->
                    if(ubicacion != null){
                        cuandoObtengaLaUltimaPosicionCorrecta(Pair(ubicacion.latitude, ubicacion.longitude))
                    }
                    else {
                        cuandoUltimaPosicionSeaNula()
                    }
                }
                .addOnFailureListener{ error ->
                    cuandoFalleAlObtenerUbicacion(error)
                }
        }
    }

    @SuppressLint("MissingPermission")
    fun obtenerUbicacion(
        alObtenerUbicacion: (Pair<Double, Double>) -> Unit,
        alObtenerAlgunError: (Exception) -> Unit,
        prioridad: Boolean = true
    ){
        val precision = if(prioridad) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if(tenemosPermisosUbicacion()){
            conexionParaObtenerUbicacion.getCurrentLocation(
                precision, CancellationTokenSource().token
            ).addOnSuccessListener { ubicacion ->
                if(ubicacion != null){
                    alObtenerUbicacion(Pair(ubicacion.latitude, ubicacion.longitude))
                }
            }
                .addOnFailureListener{ error ->
                    alObtenerAlgunError(error)
                }
        }

    }

    private fun tenemosPermisosUbicacion(): Boolean{
        return (
                ActivityCompat.checkSelfPermission(
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