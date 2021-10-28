package domain.entities.organizacion;



import domain.entities.PreguntasAdopcion.RespuestaAdopcion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="publicacion_mascota_en_adopcion")
@PrimaryKeyJoinColumn(name="id")
public class PublicacionMascotaEnAdopcion extends Publicacion{

    @OneToOne
    private Mascota mascota;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name="publicacionMascAdop_respuestas")
    private List<RespuestaAdopcion> respuestas=new ArrayList<>();

    public PublicacionMascotaEnAdopcion(Duenio duenio,Mascota mascota) {
        super(duenio, mascota.getFotos(), mascota.getDescripcion());
        this.mascota=mascota;
        setTipoPublicacion(TipoPublicacion.EN_ADOPCION);
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

    public PublicacionMascotaEnAdopcion() {
    }

}
