package domain.organizacion;

import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionMascotaPerdida extends Publicacion{
    private Usuario rescatista;
    private List<String> fotosMascota;
    private String estadoMascota;
    private LocalDateTime fechaDePublicacion;
    private String longitud;
    private String latitud;

    public PublicacionMascotaPerdida(Usuario rescatista, List<String> fotosMascota, String estadoMascota) {
        this.rescatista = rescatista;
        this.fotosMascota = fotosMascota;
        this.estadoMascota = estadoMascota;
        this.fechaDePublicacion = LocalDateTime.now();
    }

}
