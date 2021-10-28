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

    @ElementCollection
    List<String> valor=new ArrayList<>();

    public RespuestaAdopcion(PreguntaAdopcion pregunta,List<String> valor) {
        this.pregunta=pregunta;
        this.valor = valor;
    }

    public void setPregunta(PreguntaAdopcion pregunta) {
        this.pregunta = pregunta;
    }

    public void agregarValor(String valor){
        this.valor.add(valor);
    }
    public void eliminarValor(String valor){
        this.valor.remove(valor);
    }
    public List<String> getValor() {
        return valor;
    }

    public RespuestaAdopcion() {
    }
}
