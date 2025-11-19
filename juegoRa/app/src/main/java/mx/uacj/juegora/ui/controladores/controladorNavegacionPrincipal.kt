package mx.uacj.juegora.ui.controladores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.uacj.juegora.ui.pantallas.Principal
import mx.uacj.juegora.ui.pantallas.SeleccionarPantallaPista

@Composable
fun NavegadorPrincipal(modificador: Modifier = Modifier){
    var controlador_de_navegacion = rememberNavController()

    NavHost(controlador_de_navegacion, startDestination = "OpcionNavegacionPantallaPrincipal"){
        composable("OpcionNavegacionPantallaPrincipal") {
            Principal(controlador_de_navegacion, modificador)
        }

        composable("SelectorPantallaPista") {
            SeleccionarPantallaPista(controlador_de_navegacion, modificador)
        }
    }
}