package domain.organizacion;

import java.util.Map;
import java.awt.*;
import java.util.List;

public class Mascota {
    private String nombre;
    private Integer idMascota;
    private boolean sexo;
    private String apodo;
    private String descripcion;
    private Duenio duenio;
    private Integer edad;
    private boolean especie;
    private List<Image> fotos;
    private Map<String,String> caracteristicas;
    private EstadoMascota estado;

    public Mascota(String nombre, boolean sexo, String apodo, String descripcion, Integer edad, boolean especie, List<Image> fotos, Duenio duenio, Map<String,String> caracteristicas) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.edad = edad;
        this.especie = especie;
        this.fotos = fotos;
        this.duenio= duenio;
        this.caracteristicas=caracteristicas;
    }

    public void setFotos(List<Image> fotos) {
        this.fotos = fotos;
    }

    public List<Image> getFotos() {
        return fotos;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public Integer getIdMascota() {
        return idMascota;
    }
}

