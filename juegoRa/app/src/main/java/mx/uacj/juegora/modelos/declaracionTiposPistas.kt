package mx.uacj.juegora.modelos

public interface PistaGenerica{
    val tipo: TiposPistas
    val texto: String?
}

data class Informacion( // Es la pista con solo texto y posiblemente una imagen
    override val tipo: TiposPistas = TiposPistas.texto,
    override val texto: String,
    val image: String? = null

) : PistaGenerica

data class Boton(
    val texto: String,
    val direccion: PistaGenerica?
)

data class InformacionInteractiva(
    override val tipo: TiposPistas = TiposPistas.interactiva,
    override val texto: String?,
    val listaBotones: List<Boton>
): PistaGenerica