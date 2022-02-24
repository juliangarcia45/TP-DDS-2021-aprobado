package domain.entities.notificacion;
import domain.entities.entidadPersistente.EntidadPersistente;
import org.apache.commons.mail.EmailException;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="contacto")
public class Contacto extends EntidadPersistente {

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email;

    @Column
    private String telefono;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="contacto_id",referencedColumnName = "id")
    private List<MedioDeNotificacion> mediosDeNotificacion ;

    public Contacto(String nombre, String apellido, String email, String telefono, List<MedioDeNotificacion> mediosDeNotificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.mediosDeNotificacion = mediosDeNotificacion;
    }
    public void notificar(String mensaje) throws EmailException {
        for(MedioDeNotificacion medioDeNotificacion : mediosDeNotificacion){
            if(medioDeNotificacion.getEstrategiaNotificacion().getClass()==Email.class){
                medioDeNotificacion.notificar(email,mensaje);
            }else{
                medioDeNotificacion.notificar(telefono,mensaje);
            }
        }
    }

    public String getContacto(){
        return nombre+ " " + apellido +" "+ email + " " + telefono;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Contacto() {
    }

}
