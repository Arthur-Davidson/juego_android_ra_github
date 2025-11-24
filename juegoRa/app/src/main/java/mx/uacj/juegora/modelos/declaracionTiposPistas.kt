package mx.uacj.juegora.modelos

public interface PistaGenerica{
    val tipo: TiposDePistas
    val texto: String?
}

data class Informacion( // Es la pista con solo texto y posiblemente una imagen
    override val tipo: TiposDePistas = TiposDePistas.texto,
    override val texto: String,
    val imagen: Int? = null
) : PistaGenerica

data class Boton(
    val texto: String,
    val direccion: String
)

data class InformacionInteractiva(
    override val tipo: TiposDePistas = TiposDePistas.interactiva,
    override val texto: String?,
    val lista_de_botones: List<Boton>
) : PistaGenerica

data class InformacionAudio(
    override val tipo: TiposDePistas = TiposDePistas.audio,
    override val texto: String? = null
) : PistaGenerica

data class InformacionAgitar(
    override val tipo: TiposDePistas = TiposDePistas.agitar_telefono,
    val textoOculto: String,
    val meta1: Int = 25,
    val meta2: Int = 50,
) : PistaGenerica {
    override val texto: String? = null
}


