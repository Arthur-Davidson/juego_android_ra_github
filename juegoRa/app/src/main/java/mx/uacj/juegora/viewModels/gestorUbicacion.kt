package mx.uacj.juegora.viewModels

import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.uacj.juegora.repositoriosEstaticos.RepositorioUbicacion
import javax.inject.Inject

@HiltViewModel
class GestorUbicacion @Inject constructor(
    private val repositorio_ubicacion: RepositorioUbicacion
): ViewModel(){
    //private val _ubicacion_actual: MutableState<Location> = mutableStateOf(Location("juegora"))
    val ubicacion_actual: State<Location> = repositorio_ubicacion.ubicacion

    fun actualizar_ubicacion_actual(ubicacion_nueva: Location){
        repositorio_ubicacion.ubicacion.value = ubicacion_nueva
    }
}