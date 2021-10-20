package domain.organizacion;

import domain.autenticacion.Usuario;
import domain.entidadPersistente.EntidadPersistente;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="publicacion")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Publicacion extends EntidadPersistente {

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name="autor_id",referencedColumnName = "id")
    private Usuario autor;

    @ElementCollection
    private List<String> fotos;

    @Column
    private String descripcion;

    @Column(name = "fechaDePublicacion", columnDefinition = "DATE")
    private LocalDate fechaDePublicacion;

    @Column(name="estado_publicacion")
    @Enumerated
    private EstadoPublicacion estado;


    public void aprobarPublicacion(){
        this.setEstado(EstadoPublicacion.APROBADO);
    }


    public Publicacion(Usuario autor, List<String> fotos, String descripcion) {
        this.autor = autor;
        this.fotos = fotos;
        this.descripcion = descripcion;
        this.fechaDePublicacion = LocalDate.now();
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

    public LocalDate getFechaDePublicacion() {
        return fechaDePublicacion;
    }

    public void setFechaDePublicacion(LocalDate fechaDePublicacion) {
        this.fechaDePublicacion = fechaDePublicacion;
    }

    public EstadoPublicacion getEstado() {
        return estado;
    }

    public void setEstado(EstadoPublicacion estado) {
        this.estado = estado;
    }

    public Publicacion() {
    }
}