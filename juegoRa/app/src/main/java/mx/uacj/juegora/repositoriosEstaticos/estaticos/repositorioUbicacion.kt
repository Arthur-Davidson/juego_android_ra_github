package mx.uacj.juegora.repositoriosEstaticos.estaticos


import android.location.Location
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object repositorioUbicacionSingleton{
    @Provides
    @Singleton
    fun creaRepositorioGestorDeUbicacion(): RepositorioUbicacion {
        return InstanciaRepositorioUbicacion()
    }
}

class InstanciaRepositorioUbicacion(
    override val ubicacion: MutableState<Location> = mutableStateOf(Location("juegoRa"))
): RepositorioUbicacion {}

interface RepositorioUbicacion {
    val ubicacion: MutableState<Location>
}