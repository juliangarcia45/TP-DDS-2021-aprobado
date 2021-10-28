package domain.entities.organizacion;

import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.entidadPersistente.EntidadPersistente;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="publicacion_adoptante")
public class PublicacionAdoptante extends EntidadPersistente {

    @OneToOne(cascade=CascadeType.ALL)
    private Duenio interesado;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RespuestaAdopcion> respuestasPreferencias=new ArrayList<>();

    public PublicacionAdoptante(Duenio interesado, List<RespuestaAdopcion> respuestasPreferencias) {
        this.interesado = interesado;
        this.respuestasPreferencias=respuestasPreferencias;
    }

    public void agregarRespuesta(RespuestaAdopcion respuesta) {
        this.respuestasPreferencias.add(respuesta);
    }

    public void eliminarRespuesta(RespuestaAdopcion respuesta) {
        this.respuestasPreferencias.remove(respuesta);
    }

    public List<RespuestaAdopcion> getRespuestas() {
        return respuestasPreferencias;
    }

    public Duenio getInteresado() {
        return interesado;
    }
    public void setInteresado(Duenio interesado) {
        this.interesado = interesado;
    }

    public PublicacionAdoptante() {
    }
}
