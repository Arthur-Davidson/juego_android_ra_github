package mx.uacj.juegora.ui.organismos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mx.uacj.juegora.modelos.InformacionInteractiva

@Composable
fun InformacionInteractivaVista(informacion_interactiva: InformacionInteractiva){
    Column {
        Text("${informacion_interactiva.texto}")

        for(boton in informacion_interactiva.lista_de_botones){
            Text("Boton para ir a ${boton.texto}")
        }
    }
}