package mx.uacj.juegora.viewModels

import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.uacj.juegora.repositoriosEstaticos.estaticos.RepositorioUbicacion
import javax.inject.Inject

@HiltViewModel
class GestorUbicacion @Inject constructor(
    private val reposUbicacion: RepositorioUbicacion
): ViewModel(){
    private val _ubicacionActual: MutableState<Location> = mutableStateOf(Location("juegoRa"))
    val ubicacionActual: State<Location> = _ubicacionActual

    fun actualizarUbicacionActual(ubicacionNueva: Location){
        _ubicacionActual.value = ubicacionNueva
    }
}