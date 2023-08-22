package domain.entities.PreguntasAdopcion;

import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="opcion")
public class Opcion extends EntidadPersistente {

    @Column(name = "valor")
    String valor;

    @ManyToOne
    @JoinColumn(name = "pregunta", referencedColumnName = "id")
    PreguntaAdopcion pregunta;


    public Opcion(String valor, PreguntaAdopcion pregunta) {
        this.valor = valor;
        this.pregunta = pregunta;
    }

    public Opcion() {}

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public PreguntaAdopcion getPregunta() {
        return pregunta;
    }

    public void setPregunta(PreguntaAdopcion pregunta) {
        this.pregunta = pregunta;
    }
}
