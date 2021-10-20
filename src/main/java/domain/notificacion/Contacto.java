package domain.notificacion;
import domain.entidadPersistente.EntidadPersistente;

import javax.persistence.*;
import java.beans.ConstructorProperties;
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
    private Integer telefono;

    @OneToMany(mappedBy = "contactoANotificar",cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    private List<MedioDeNotificacion> mediosDeNotificacion ;

    public Contacto(String nombre, String apellido, String email, Integer telefono, List<MedioDeNotificacion> mediosDeNotificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.mediosDeNotificacion = mediosDeNotificacion;
    }
    public void notificar(String mensaje){
        for(MedioDeNotificacion medioDeNotificacion : mediosDeNotificacion){
            medioDeNotificacion.notificar(telefono,mensaje);
        }
    }

    public String getContacto(){
        return nombre+ " " + apellido +" "+ email + " " + telefono.toString();
    }
    public Contacto() {
    }

}
