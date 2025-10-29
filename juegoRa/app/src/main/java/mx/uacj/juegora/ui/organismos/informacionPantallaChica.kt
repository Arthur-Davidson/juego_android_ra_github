package mx.uacj.juegora.ui.organismos

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import mx.uacj.juegora.modelos.Informacion

@Composable
fun InformacionVista(informacionAMostrar: Informacion){
    Column {
        Text(informacionAMostrar.texto)
    }
}