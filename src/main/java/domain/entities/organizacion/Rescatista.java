package domain.entities.organizacion;

import domain.entities.autenticacion.Usuario;
import domain.entities.fotos.Foto;
import domain.entities.hogaresAPI.HogarDeTransito;
import domain.entities.hogaresAPI.HogaresResponseApi;

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

    @ElementCollection
    List<Foto> fotoMascota;

    @Transient
    HogaresResponseApi hogaresResponseApi;

    public Rescatista(String usuario, String contrasenia) {
        super(usuario, contrasenia);
        setTipoDeUsuario(TipoUsuario.RESCATISTA);
    }

   public void notificarDuenio(Mascota unaMascota){
       unaMascota.notificarDuenio("encontramos a tu mascota");
   }

   public File getQrCode(Mascota mascotaPerdida){
      return mascotaPerdida.generateQR();
   }

   public void informarMascotaPerdida(Ubicacion puntoEncuentro){
       GestorPublicaciones.generarPublicacionMascotaPerdida(this,this.getDescripcionMascota(),this.getFotoMascota(),puntoEncuentro);
   }
   
    public void setHogaresResponseApi(HogaresResponseApi hogaresResponseApi) {
        this.hogaresResponseApi = hogaresResponseApi;
    }

    public List<HogarDeTransito> buscarHogarDeTransito(String latitud, String longitud) throws IOException {
        return this.hogaresResponseApi.getRespuestaApi();
    }

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public List<Foto> getFotoMascota() {
        return fotoMascota;
    }

    public void setFotoMascota(Foto foto) {
        fotoMascota.add(foto);
    }

    public Rescatista() {
    }

}
