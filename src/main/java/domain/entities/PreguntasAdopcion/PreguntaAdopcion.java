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

    @ElementCollection
    private List<String> opciones=new ArrayList<>();

    @ManyToOne
    private Organizacion organizacion;

    @Column(name="tipo_pregunta")
    @Enumerated(value = EnumType.STRING)
    private TipoPregunta tipo;

    public PreguntaAdopcion(String pregunta,  TipoPregunta tipo){
        this.pregunta = pregunta;
        this.tipo = tipo;
    }

    public PreguntaAdopcion() {
    }

    public void agregarOpcion(String opcion){
        opciones.add(opcion);
    }

    public void eliminarOpcion(String opcion){
        opciones.remove(opcion);
    }

    public void asignarOrganizacion(Organizacion organizacion){
        this.organizacion = organizacion;
    }

    public Organizacion getOrganizacion(){
        return this.organizacion;
    }
}