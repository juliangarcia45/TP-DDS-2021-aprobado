package domain.entities.PreguntasAdopcion;

import domain.entities.entidadPersistente.EntidadPersistente;
import domain.entities.organizacion.Organizacion;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="pregunta_de_adopcion")
public class PreguntaAdopcion extends EntidadPersistente {

    @Column
    private String pregunta;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Opcion> opciones=new ArrayList<>();

    @Column(name="tipo_pregunta")
    @Enumerated(value = EnumType.STRING)
    private TipoPregunta tipo;

    public PreguntaAdopcion(String pregunta,  TipoPregunta tipo){
        this.pregunta = pregunta;
        this.tipo = tipo;
    }

    public PreguntaAdopcion() {
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public List<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<Opcion> opciones) {
        this.opciones = opciones;
    }

    public TipoPregunta getTipo() {
        return tipo;
    }

    public void setTipo(TipoPregunta tipo) {
        this.tipo = tipo;
    }
}