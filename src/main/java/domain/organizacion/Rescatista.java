package domain.organizacion;

import domain.autenticacion.Usuario;
import domain.hogaresAPI.HogarDeTransito;
import domain.hogaresAPI.HogaresResponseApi;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Rescatista extends Usuario {

    String descripcionMascota;
    String direccionRescatista;
    String direccionEncuentroMascota;
    String fotoMascota;

    public void setHogaresResponseApi(HogaresResponseApi hogaresResponseApi) {
        this.hogaresResponseApi = hogaresResponseApi;
    }

    HogaresResponseApi hogaresResponseApi;
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
    public List<HogarDeTransito> buscarHogarDeTransito(String latitud, String longitud) throws IOException {
        return this.hogaresResponseApi.getRespuestaApi();
    }


}
