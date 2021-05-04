package domain.organizacion;

import java.awt.*;
import java.util.List;

public class Mascota {
    private String nombre;
    private boolean sexo;
    private String apodo;
    private String descripcion;
    private Duenio duenio;
    private Integer edad;
    private boolean especie;
    private List<Image> fotos;

    public Mascota(String nombre, boolean sexo, String apodo, String descripcion, Integer edad, boolean especie, List<Image> fotos, Duenio duenio) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.apodo = apodo;
        this.descripcion = descripcion;
        this.edad = edad;
        this.especie = especie;
        this.fotos = fotos;
        this.duenio= duenio;
    }
}
