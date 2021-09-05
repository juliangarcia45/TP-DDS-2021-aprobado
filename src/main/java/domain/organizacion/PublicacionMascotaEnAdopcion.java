package domain.organizacion;



import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.notificacion.Contacto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PublicacionMascotaEnAdopcion extends Publicacion{
    private Mascota mascota;
    private List<RespuestaAdopcion> respuestas=new ArrayList<>();

    public PublicacionMascotaEnAdopcion(Duenio duenio,Mascota mascota) {
        super(duenio, mascota.getFotos(), mascota.getDescripcion());
        this.mascota=mascota;
    }

    public void quieroAdoptar(String contacto) {
        this.getMascota().notificarDuenio("Quieren adoptar a tu mascota"+ contacto );
    }

    public Mascota getMascota() {
        return mascota;
    }
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
    public void agregarRespuesta(RespuestaAdopcion respuesta) {
        this.respuestas.add(respuesta);
    }

    public void eliminarRespuesta(RespuestaAdopcion respuesta) {
        this.respuestas.remove(respuesta);
    }

    public List<RespuestaAdopcion> getRespuestas() {
        return respuestas;
    }

}
