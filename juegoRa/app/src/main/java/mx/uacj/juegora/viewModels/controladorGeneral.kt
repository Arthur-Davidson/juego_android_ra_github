package mx.uacj.juegora.viewModels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.uacj.juegora.modelos.Pista
import mx.uacj.juegora.repositoriosEstaticos.RepositorioInformacionGeneral
import javax.inject.Inject

@HiltViewModel
class ControladorGeneral @Inject constructor(
    private val infomracion_general: RepositorioInformacionGeneral
)  : ViewModel()  {

    fun el_usuario_ha_identificado_la_pista(pista: Pista){
        infomracion_general.pistas_identificadas.value
    }
}