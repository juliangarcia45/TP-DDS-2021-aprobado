package domain.entities.PreguntasAdopcion;

import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="respuesta_de_pregunta_de_adopcion")
public class RespuestaAdopcion extends EntidadPersistente {
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="pregunta_id", referencedColumnName = "id")
    PreguntaAdopcion pregunta;

    @ManyToMany
    @JoinTable(name = "respuesta_con_opciones",
            joinColumns = {
                    @JoinColumn(name = "respuesta_id", referencedColumnName = "id") },
            inverseJoinColumns = {
                    @JoinColumn(name = "opcion_id", referencedColumnName = "id") })
    List<Opcion> opciones=new ArrayList<>();

    public PreguntaAdopcion getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaAdopcion pregunta) {
        this.pregunta = pregunta;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public RespuestaAdopcion(PreguntaAdopcion pregunta, List<Opcion> opciones) {
        this.pregunta = pregunta;
        this.opciones = opciones;
    }

    public RespuestaAdopcion() {
    }
}
