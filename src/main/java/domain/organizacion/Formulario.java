package domain.organizacion;
import java.util.List;


public class Formulario{

    private Rescatista rescatista;
    private String  descripcionMascota;
    private String direccionEncuentroMascota;
    private List<String> fotoMascota;

  
    
    public Formulario(Rescatista rescatista , String descripcion , String direccionEncuentroMascota , List<String> fotoMascota){
        this.rescatista = rescatista;
        this.descripcionMascota = descripcion;
        this.direccionEncuentroMascota = direccionEncuentroMascota;
        this.fotoMascota = fotoMascota;
    }

    
}