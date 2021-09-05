package domain.PreguntasAdopcion;

import domain.organizacion.Organizacion;

import java.util.ArrayList;
import java.util.List;

public class PreguntaAdopcion {
    private String pregunta;
    private List<String> opciones=new ArrayList<>();
    private Organizacion organizacion;
    private TipoPregunta tipo;

    public PreguntaAdopcion(String pregunta,  TipoPregunta tipo){
        this.pregunta = pregunta;
        this.tipo = tipo;
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