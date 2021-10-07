package domain.organizacion;

import domain.autenticacion.Usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="publicacion_mascota_perdida")
@PrimaryKeyJoinColumn(name="id")
public class PublicacionMascotaPerdida extends Publicacion{

    public PublicacionMascotaPerdida(Usuario rescatista, List<String> fotosMascota, String estadoMascota) {
        super(rescatista, fotosMascota, estadoMascota);
    }

    public void esMiMascota(String contactoDuenio){
        this.getAutor().getMediosDeContacto().stream().forEach(contacto -> contacto.notificar("El duenio encontro la publicacion de su mascota"+ contactoDuenio));
    }

}
