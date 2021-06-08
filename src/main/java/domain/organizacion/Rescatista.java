package domain.organizacion;

import domain.autenticacion.Usuario;

public class Rescatista extends Usuario {

    String descripcionMascota;
    String direccionRescatista;
    String direccionEncuentroMascota;
    String fotoMascota;

    public Rescatista(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

   /* public rellenarFormulario(String descripcionMascota, String direccionRescatista, String fotoMascota, String fotoMascota){
       this.descripcionMascota= descripcionMascota;
        this.descripcionMascota= descripcionMascota;
        this.descripcionMascota= descripcionMascota;
        this.descripcionMascota= descripcionMascota;
    }
*/


}
