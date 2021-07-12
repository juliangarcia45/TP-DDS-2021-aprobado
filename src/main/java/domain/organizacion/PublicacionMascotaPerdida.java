package domain.organizacion;

import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionMascotaPerdida extends Publicacion{

    private String longitud;
    private String latitud;


    public PublicacionMascotaPerdida(Usuario rescatista, List<String> fotosMascota, String estadoMascota) {
        super(rescatista, fotosMascota, estadoMascota);
    }

}
