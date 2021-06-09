package domain.notificacion;
import java.util.List;

public class Contacto {
    private String nombre;
    private String apellido;
    private String email;
    private Integer telefono;
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
