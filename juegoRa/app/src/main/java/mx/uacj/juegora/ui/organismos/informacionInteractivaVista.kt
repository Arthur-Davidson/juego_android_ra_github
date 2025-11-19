package mx.uacj.juegora.ui.organismos

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import mx.uacj.juegora.modelos.InformacionInteractiva
import mx.uacj.juegora.viewModels.ControladorGeneral

@Composable
fun InformacionInteractivaVista(navegador: NavHostController, informacion_interactiva: InformacionInteractiva){
    val controlador_general: ControladorGeneral = hiltViewModel()
    Column {
        Text("${informacion_interactiva.texto}")

        for(boton in informacion_interactiva.lista_de_botones){

            Text("${boton.texto}", modifier = Modifier.clickable {
                controlador_general.seleccionar_pista(boton.direccion)
                navegador.navigate("SelectorPantallaPista")
            })
        }
    }
}