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

    @Column
    String direccionRescatista;

    @OneToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="puntoEncuentro_id",referencedColumnName = "id")
    Ubicacion direccionEncuentroMascota;

    @ElementCollection
    List<Foto> fotoMascota;

    @Transient
    HogaresResponseApi hogaresResponseApi;

    public Rescatista(String usuario, String contrasenia,String direccionRescatista) {
        super(usuario, contrasenia);
        setDireccionRescatista(direccionRescatista);
        setTipoUsuario(TipoUsuario.RESCATISTA);
    }

   public void notificarDuenio(Mascota unaMascota){
       unaMascota.notificarDuenio("encontramos a tu mascota");
   }

   public File getQrCode(Mascota mascotaPerdida){
      return mascotaPerdida.generateQR();
   }

   public void informarMascotaPerdida(){
       GestorPublicaciones.generarPublicacionMascotaPerdida(this,this.getDescripcionMascota(),this.getFotoMascota());
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

    public String getDescripcionMascota() {
        return descripcionMascota;
    }

    public void setDescripcionMascota(String descripcionMascota) {
        this.descripcionMascota = descripcionMascota;
    }

    public String getDireccionRescatista() {
        return direccionRescatista;
    }

    public void setDireccionRescatista(String direccionRescatista) {
        this.direccionRescatista = direccionRescatista;
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
