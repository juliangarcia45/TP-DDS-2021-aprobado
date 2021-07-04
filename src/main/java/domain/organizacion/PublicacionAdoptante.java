package domain.organizacion;

import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionAdoptante extends Publicacion{
    private LocalDateTime fechaDePublicacion;
    private Usuario usuarioAdoptante;
    private List<String> preferencias;
    private List<String> comodidades;

    public PublicacionAdoptante( Usuario usuarioAdoptante, String descripcion, List<String> fotosAdoptante) {
        this.fechaDePublicacion = LocalDateTime.now();
        this.usuarioAdoptante = usuarioAdoptante;

    }


}
