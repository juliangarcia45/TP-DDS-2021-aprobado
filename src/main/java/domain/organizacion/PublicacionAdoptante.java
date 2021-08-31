package domain.organizacion;

import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionAdoptante extends Publicacion{
    private List<String> preferencias;
    private List<String> comodidades;

    public PublicacionAdoptante( Usuario usuarioAdoptante, String descripcion, List<String> fotosAdoptante) {
        super(usuarioAdoptante, fotosAdoptante, descripcion);
    }



}
