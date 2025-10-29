package mx.uacj.juegora.ui.organismos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mx.uacj.juegora.modelos.InformacionInteractiva

@Composable
fun InformacionInteractivaVista(informacionInteractiva: InformacionInteractiva){
    Column {
        Text("${informacionInteractiva.texto}")

        for (boton in informacionInteractiva.listaBotones){
            Text("Boton para ir a ${boton.texto}")
        }
    }
}