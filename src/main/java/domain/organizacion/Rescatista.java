package domain.organizacion;

import domain.autenticacion.Usuario;
import domain.hogaresAPI.HogarDeTransito;
import domain.hogaresAPI.HogaresResponseApi;
import domain.notificacion.Contacto;

import javax.persistence.*;
import java.io.File;
import java.io.IOException;
import java.util.List;


@Entity
@Table(name="rescatista")
@PrimaryKeyJoinColumn(name="id")
public class Rescatista extends Usuario {

    @Column
    String descripcionMascota;

    @Column
    String direccionRescatista;

    @OneToOne
    Ubicacion direccionEncuentroMascota;

    @Transient
    List<String> fotoMascota;

    @Transient
    HogaresResponseApi hogaresResponseApi;

    public Rescatista(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

   public void notificarDuenio(Mascota unaMascota){
       unaMascota.notificarDuenio("encontramos a tu mascota");
   }

   public File getQrCode(Mascota mascotaPerdida){
      return mascotaPerdida.generateQR();
   }

   public void informarMascotaPerdida(Mascota unaMascota){
       GestorPublicaciones.generarPublicacionMascotaPerdida(this,unaMascota.getDescripcion(),unaMascota.getFotos());
   }
   
    public void setHogaresResponseApi(HogaresResponseApi hogaresResponseApi) {
        this.hogaresResponseApi = hogaresResponseApi;
    }

    public List<HogarDeTransito> buscarHogarDeTransito(String latitud, String longitud) throws IOException {
        return this.hogaresResponseApi.getRespuestaApi();
    }


    public Ubicacion getDireccionEncuentroMascota() {
        return direccionEncuentroMascota;
    }
    public void setDireccionEncuentroMascota(Ubicacion direccionEncuentroMascota) {
        this.direccionEncuentroMascota = direccionEncuentroMascota;
    }

}
