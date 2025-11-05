package mx.uacj.juegora.gestorPermisos

import android.Manifest
import androidx.collection.emptyObjectList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.common.util.CollectionUtils.listOf
import java.util.Collections.emptyList

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ParaLaSolicitudDePermisos(
    conPermisosObtenidos: () -> Unit,
    sinPermisosObtenidos: () -> Unit,
    permisosRevocados: () -> Unit
){
    val estadosPermisos = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ){ listaPermisos ->
        var tenerPermisosObtenidos: Boolean = false // Variante bandera

        for (permiso in listaPermisos.values){
            if(!permiso){
                tenerPermisosObtenidos = false
                break
            }
            else{
                tenerPermisosObtenidos = true
            }
        }

        if (tenerPermisosObtenidos){
            conPermisosObtenidos.invoke()
        }
        else{
            sinPermisosObtenidos.invoke()
        }

    }

    LaunchedEffect(key1 = estadosPermisos) {
        val tenerPermisosRevocados = estadosPermisos.revokedPermissions.size == estadosPermisos.permissions.size

        var listaPermisosPorPedir: List<PermissionState> = emptyList<PermissionState>()

        for(permiso in estadosPermisos.permissions){
            if (!permiso.status.isGranted){
                listaPermisosPorPedir.append(permiso)
            }
        }

        if(!listaPermisosPorPedir.isEmpty()){
            estadosPermisos.launchMultiplePermissionRequest()
        }

        if (tenerPermisosRevocados){
            permisosRevocados()
        }
        else{
            sinPermisosObtenidos
        }

    }
}