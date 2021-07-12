package domain.organizacion;

import domain.autenticacion.Usuario;
import domain.hogaresAPI.HogarDeTransito;
import domain.hogaresAPI.HogaresResponseApi;
import domain.notificacion.Contacto;
import java.io.IOException;
import java.util.List;

public class Rescatista extends Usuario {

    String descripcionMascota;
    String direccionRescatista;
    String direccionEncuentroMascota;
    List<String> fotoMascota;
    HogaresResponseApi hogaresResponseApi;

    public Rescatista(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

   public void escanearQr(Mascota mascotaPerdida){
        List <Contacto> listaContactosDuenio = mascotaPerdida.getDuenio().getMediosDeContacto();
       for(Contacto contacto : listaContactosDuenio){
           contacto.notificar("Encontre a tu mascota");
       }

   }
    public void setHogaresResponseApi(HogaresResponseApi hogaresResponseApi) {
        this.hogaresResponseApi = hogaresResponseApi;
    }

    public List<HogarDeTransito> buscarHogarDeTransito(String latitud, String longitud) throws IOException {
        return this.hogaresResponseApi.getRespuestaApi();
    }

}
