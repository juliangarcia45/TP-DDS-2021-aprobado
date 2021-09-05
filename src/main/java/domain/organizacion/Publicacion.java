package domain.organizacion;

import domain.autenticacion.Usuario;
import java.time.LocalDateTime;
import java.util.List;

public abstract class Publicacion {
    private Usuario autor;
    private List<String> fotos;
    private String descripcion;
    private LocalDateTime fechaDePublicacion;
    private EstadoPublicacion estado;


    public void aprobarPublicacion(){
        this.setEstado(EstadoPublicacion.APROBADO);
    }


    public Publicacion(Usuario autor, List<String> fotos, String descripcion) {
        this.autor = autor;
        this.fotos = fotos;
        this.descripcion = descripcion;
        this.fechaDePublicacion = LocalDateTime.now();
        this.setEstado(EstadoPublicacion.EN_ESPERA);
    }
    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Usuario usuario) {
        this.autor = usuario;
    }

    public List<String> getFotos() {
        return fotos;
    }

    public void setFotos(List<String> fotos) {
        this.fotos = fotos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDateTime getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDateTime fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }
}