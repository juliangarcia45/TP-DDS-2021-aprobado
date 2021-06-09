package domain.organizacion;

import domain.autenticacion.Usuario;
import domain.hogaresAPI.HogarDeTransito;
import domain.hogaresAPI.HogaresResponseApi;
import domain.notificacion.Contacto;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Rescatista extends Usuario {

    private String direccionRescatista;
   
    
    HogaresResponseApi hogaresResponseApi;
    public Rescatista(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public void setHogaresResponseApi(HogaresResponseApi hogaresResponseApi) {
        this.hogaresResponseApi = hogaresResponseApi;
    }

    public List<HogarDeTransito> buscarHogarDeTransito(String latitud, String longitud) throws IOException {
        return this.hogaresResponseApi.getRespuestaApi();
    }
    
    public void reportarMascotaPerdida(List<String> fotosMascota,String descripcion,String direccionEncuentroMascota,Mascota mascota){
        Formulario formulario = this.rellenarFormulario(fotosMascota, descripcion, direccionEncuentroMascota);

        Contacto contactoDuenio= this.escanearQR(mascota);
        notificarDuenio(contactoDuenio);
    }

    
    public Contacto escanearQR(Mascota mascotaPerdida){
       return mascotaPerdida.getDuenio().getMediodeContacto();
    }
  
    public Formulario rellenarFormulario(List<String> fotoMascota, String descripcion ,  String direccionEncuentroMascota){
        return new Formulario(this, descripcion, direccionEncuentroMascota, fotoMascota);
    }

    public void notificarDuenio(Contacto contacto){
        contacto.notificar("flaco encontramos a tu mascota");
    }

}
