package domain.autenticacion;

import domain.notificacion.Contacto;
import domain.organizacion.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="usuario")
@Inheritance(strategy=InheritanceType.JOINED)
public abstract class Usuario {

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String usuario;

    @Column
    private String contrasenia;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Transient
    private List<Contacto>  mediosDeContacto;
    @Transient
    private  Documento documento;
    @Transient
    private Date fechaNacimiento;



    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        mediosDeContacto = new ArrayList<>();
    }

    public void registrarUsuario(Usuario usuario){Usuario registrado = RegistroDeUsuarios.registrar(usuario);}


    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public List<Contacto> getMediosDeContacto() {
        return mediosDeContacto;
    }

    public void setMediosDeContacto(List<Contacto> mediosDeContacto) {
        this.mediosDeContacto = mediosDeContacto;
    }

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
