package mx.uacj.juegora.gestor_permisos

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.util.fastFilter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.util.CollectionUtils.listOf

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ParaLaSolictudDePermisos(
    conPermisosObtenidos: () -> Unit,
    sinPermisosObtenidos: () -> Unit,
    conPermisosRevocados: () -> Unit
){
    val estadosPermisos = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ){ listaPermisos ->
        var tengoTodosLosPermisos: Boolean = false // Variable bandera o flag

        for (permiso in listaPermisos.values){
            if(!permiso){
                tengoTodosLosPermisos = false
                break
            }
            else {
                tengoTodosLosPermisos = true
            }
        }

        if(tengoTodosLosPermisos){
            conPermisosObtenidos.invoke()
        }
        else {
            sinPermisosObtenidos.invoke()
        }

    }

    LaunchedEffect(key1 = estadosPermisos) {
        val tengoPermisosRevocados = estadosPermisos.revokedPermissions.size == estadosPermisos.permissions.size

        estadosPermisos.permissions

        val listaPermisosPorPedir = estadosPermisos.permissions.fastFilter { permiso ->
            !permiso.status.isGranted
        }

        if(!listaPermisosPorPedir.isEmpty()){
            estadosPermisos.launchMultiplePermissionRequest()
        }

        if(tengoPermisosRevocados){
            conPermisosRevocados()
        }
        else {
            if(estadosPermisos.allPermissionsGranted){
                conPermisosObtenidos()
            }
            else {
                sinPermisosObtenidos()
            }
        }
    }
}