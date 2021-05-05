package domain.organizacion;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Mascota {
    private String nombre;
    private boolean sexo;
    private String apodo;
    private String descripcion;
    private Duenio duenio;
    private Integer edad;
    private boolean especie;
    private List<Image> fotos;
    private Map<String,String> caracteristica;
    public Mascota(String nombre, boolean sexo, String apodo, String descripcion, Integer edad, boolean especie, List<Image> fotos, Duenio duenio , Map<String,String> caracteristica) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.edad = edad;
        this.especie = especie;
        this.fotos = fotos;
        this.duenio= duenio;
        this.caracteristica = caracteristica;
    }
    public List<Image> getFotos() {
        return fotos;
    }

    public void setFotos(List<Image> fotos) {
        this.fotos = fotos;
    }




}
