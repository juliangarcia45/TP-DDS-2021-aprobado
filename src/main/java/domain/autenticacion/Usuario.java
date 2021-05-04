package domain.autenticacion;

import domain.notificacion.Contacto;
import domain.organizacion.Documento;

import java.util.Date;
import java.util.List;

public abstract class Usuario {
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private List<Contacto> mediosDeContacto;
    private Documento documento;
    private Date fechaNacimiento;

    public Usuario(String usuario, String contrasenia, String nombre, String apellido, List<Contacto> mediosDeContacto, Documento documento, Date fechaNacimiento) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.mediosDeContacto = mediosDeContacto;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
    }
}
