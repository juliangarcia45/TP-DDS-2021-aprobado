package domain.entities.notificacion;
import domain.entities.entidadPersistente.EntidadPersistente;

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
    private Integer telefono;

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name="contacto_id",referencedColumnName = "id")
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
