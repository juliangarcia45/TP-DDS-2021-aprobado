package domain.entities.organizacion;

import domain.entities.autenticacion.Usuario;
import domain.entities.fotos.Foto;
import org.apache.commons.mail.EmailException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="publicacion_mascota_perdida")
@PrimaryKeyJoinColumn(name="id")
public class PublicacionMascotaPerdida extends Publicacion{

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name="puntoEncuentro_id",referencedColumnName = "id")
    Ubicacion direccionEncuentroMascota;

    public PublicacionMascotaPerdida(Rescatista rescatista, List<Foto> fotosMascota, String estadoMascota, Ubicacion puntoEncuentro) {
        super(rescatista, fotosMascota, estadoMascota);
        setDireccionEncuentroMascota(puntoEncuentro);
        setTipoPublicacion(TipoPublicacion.PERDIDA);
    }

    public void esMiMascota(String contactoDuenio){
        this.getAutor().getMediosDeContacto().stream().forEach(contacto -> {
            try {
                contacto.notificar("El duenio encontro la publicacion de su mascota"+ contactoDuenio);
            } catch (EmailException e) {
                e.printStackTrace();
            }
        });
    }
    public Ubicacion getDireccionEncuentroMascota() {
        return direccionEncuentroMascota;
    }

    public void setDireccionEncuentroMascota(Ubicacion direccionEncuentroMascota) {
        this.direccionEncuentroMascota = direccionEncuentroMascota;
    }

    public PublicacionMascotaPerdida() {
    }

}
