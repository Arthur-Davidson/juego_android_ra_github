package mx.uacj.juegora.ui.controladores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.uacj.juegora.ui.pantallas.Principal

@Composable
fun NavegadorPrincipal(modificador: Modifier = Modifier){
    var controlador_de_nacegacion = rememberNavController()

    NavHost(controlador_de_nacegacion, startDestination = "OpcionNavegacionPantallaPrincipal"){
        composable("OpcionNavegacionPantallaPrincipal") {
            Principal(modificador)
        }
    }
}