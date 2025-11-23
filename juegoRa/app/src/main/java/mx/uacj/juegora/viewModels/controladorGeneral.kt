package mx.uacj.juegora.viewModels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.sourceInformation
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.uacj.juegora.modelos.Pista
import mx.uacj.juegora.repositoriosEstaticos.RepositorioInformacionGeneral
import mx.uacj.juegora.repositoriosEstaticos.estaticos.RepositorioPruebas.pistas
import javax.inject.Inject

@HiltViewModel
class ControladorGeneral @Inject constructor(
    private val infomracion_general: RepositorioInformacionGeneral
)  : ViewModel()  {

    var pista_actual: State<Pista?> = infomracion_general.pista_seleccionada
    var bienvenidaMostrada = mutableStateOf(false)
    fun seleccionar_pista(pista: Pista){
        infomracion_general.pista_seleccionada.value = pista
    }

    fun seleccionar_pista(pista_id: String){
        for(pista in pistas){
            if(pista.nombre == pista_id){
                infomracion_general.pista_seleccionada.value = pista
                break
            }
        }
    }


    fun el_usuario_ha_identificado_la_pista(pista: Pista){
        infomracion_general.pistas_identificadas.value
    }
}


