package domain.organizacion;



import java.time.LocalDateTime;
import java.util.List;

public class PublicacionMascotaEnAdopcion extends Publicacion{

    public PublicacionMascotaEnAdopcion(Duenio duenio,String descripcionMascota, List<String> fotosMascota) {
        super(duenio, fotosMascota, descripcionMascota);
    }

}
