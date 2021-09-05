package domain.organizacion;

import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class PublicacionMascotaPerdida extends Publicacion{

    private String longitud;
    private String latitud;


    public PublicacionMascotaPerdida(Usuario rescatista, List<String> fotosMascota, String estadoMascota) {
        super(rescatista, fotosMascota, estadoMascota);
    }

    public void esMiMascota(String contactoDuenio){
        this.getAutor().getMediosDeContacto().stream().forEach(contacto -> contacto.notificar("El duenio encontro la publicacion de su mascota"+ contactoDuenio));
    }

}
