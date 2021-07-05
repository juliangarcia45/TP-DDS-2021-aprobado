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
    private  Documento documento;
    private Date fechaNacimiento;
    private Organizacion organizacionAsociada;


    public Usuario(String usuario, String contrasenia) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        mediosDeContacto = new ArrayList<>();
    }

    public void informarMascotaPerdida (List<String> fotos, String descripcion ){

         GeneradorPublicaciones.generarPublicacionMascotaPerdida(this, descripcion , fotos);

    }
    //esto de buscar mascota perdida creo que va en duenio
    public List<Publicacion> buscarMascotaPerdida(){
       return this.getOrganizacionAsociada().getListaPublicaciones();
    }

    public void contactarAlQueLaRescato(Publicacion publicacion){
        this.contactar(publicacion.getUsuario());
    }

    public void contactar(Usuario usuario){
        List <Contacto> listaContactosDuenio = usuario.getMediosDeContacto();
        for(Contacto contacto : listaContactosDuenio){
            contacto.notificar(this.getNombre() + " " + this.getApellido() + " se reportó como el dueño de la mascota que encontraste, sus contactos son: " + this.getMediosDeContacto() );
        }
        // contactarlo
        // con eso el rescatista tendria que recibir numero de telefono y mail del dueño de la mascota
    }
    public void darEnAdopcion(String descripcion, List<String> fotos){
        GeneradorPublicaciones.generarPublicacionMascotaEnAdopcion((Duenio) this,descripcion,fotos);

    }

    public void adoptarMascota(Mascota mascota) {
        List <Contacto> mediosDeContacto = mascota.getDuenio().getMediosDeContacto();
        for(Contacto contacto : mediosDeContacto){
            contacto.notificar("Quieren adoptar a tu mascota");
        }
    }

    public PublicacionAdoptante quieroAdoptarUnaMascota(String descripcion , List<String> fotos){
        return GeneradorPublicaciones.generarPublicacionAdoptante(this,descripcion,fotos);
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
