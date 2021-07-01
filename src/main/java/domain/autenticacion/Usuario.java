package domain.autenticacion;

import domain.notificacion.Contacto;
import domain.organizacion.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Usuario {
    private String usuario;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private List<Contacto>  mediosDeContacto;
    private Documento documento;
    private Date fechaNacimiento;
    private Organizacion organizacionAsociada;
    private GeneradorPublicaciones generadorPublicaciones;


    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        mediosDeContacto = new ArrayList<>();
    }

    public void informarMascotaPerdida(String estadoMascota, List<String> fotos, String descripcion ){

         generadorPublicaciones.generarPublicacion(this, estadoMascota, fotos , descripcion);

    }
    public List<Publicacion> buscarMascotaPerdida(){
       return this.getOrganizacionAsociada().getListaPublicaciones();
    }
    public void contactarAlQueLaRescato(Publicacion publicacion){
        this.contactar(publicacion.getAutor());
    }
    public void contactar(Usuario usuario){
        // contactarlo
    }
    public void darEnAdopcion(Mascota mascota){
        generadorPublicaciones.generarPublicacion();
        mascota.setEstado("En adopcion")

    }
    public void quieroAdoptarUnaMascota(){
        generadorPublicaciones.generarPublicacion();
    }





    public Organizacion getOrganizacionAsociada() {
        return organizacionAsociada;
    }
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
    public Contacto getMediodeContacto(){
        return mediosDeContacto.get(0);
    }

    public List<Contacto> getMediosDeContacto() {
        return mediosDeContacto;
    }

    public void setMedioDeContacto(Contacto contacto){
        this.mediosDeContacto.add(contacto);
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
