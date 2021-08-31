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

    public void setDuenio(Duenio duenio) {
        this.duenio = duenio;
    }

    private Duenio duenio;
    private Integer edad;

    public String getDescripcion() {
        return descripcion;
    }

    private boolean especie;
    private List<String> fotos;
    private Map<String,String> caracteristicas;
    private EstadoMascota estadoMascota;

    public Mascota(String nombre, boolean sexo, String apodo, String descripcion, Integer edad, boolean especie, List<String> fotos, Duenio duenio, Map<String,String> caracteristicas) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.edad = edad;
        this.especie = especie;
        this.fotos = fotos;
        this.duenio= duenio;
        this.caracteristicas=caracteristicas;
        this.estadoMascota=new MascotaFeliz();
    }

    public void setEstado( EstadoMascota nuevoEstadoMascota ) {
        this.estadoMascota = nuevoEstadoMascota;
    }


    public void mostrarEstado() {
        this.estadoMascota.mostrarEstado();
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public Duenio getDuenio() {
        return duenio;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

}

