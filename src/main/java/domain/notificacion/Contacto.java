package domain.notificacion;
import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.List;

@Entity
@Table(name="contacto")
public class Contacto {
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String nombre;

    @Column
    private String apellido;

    @Column
    private String email;

    @Column
    private Integer telefono;

    @Transient
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

}
