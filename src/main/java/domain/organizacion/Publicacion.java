package domain.organizacion;

import domain.autenticacion.Usuario;

import java.time.LocalDateTime;
import java.util.List;

public class Publicacion {
    private Usuario autor;
    private LocalDateTime fechaDePublicacion;
    private List<String> fotosMascota;
    private String estadoMascota;
    private String descripcion;

    public Publicacion(Usuario autor, String estadoMascota, List<String> fotosMascota,String descripcion) {
        this.autor = autor;
        this.fechaDePublicacion = LocalDateTime.now();
        this.fotosMascota = fotosMascota;
        this.estadoMascota = estadoMascota;
        this.descripcion= descripcion;
    }

    public Usuario getAutor() {
        return autor;
    }

    public void setAutor(Rescatista autor) {
        this.autor = autor;
    }

    public LocalDateTime getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDateTime fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }
}
