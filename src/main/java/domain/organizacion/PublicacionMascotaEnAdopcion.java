package domain.organizacion;



import java.time.LocalDateTime;
import java.util.List;

public class PublicacionMascotaEnAdopcion extends Publicacion{
    private Duenio duenio;
    private List<String> fotosMascota;
    private String descripcionMascota;
    private LocalDateTime fechaDePublicacion;

    public PublicacionMascotaEnAdopcion(Duenio duenio,String descripcionMascota, List<String> fotosMascota) {
        this.duenio = duenio;
        this.fotosMascota = fotosMascota;
        this.descripcionMascota = descripcionMascota;
        this.fechaDePublicacion = LocalDateTime.now();
    }

}
