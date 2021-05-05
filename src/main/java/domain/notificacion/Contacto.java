package domain.notificacion;

import java.util.List;

public class Contacto {
    private String nombre;
    private String apellido;
    private String email;
    private Double telefono;
    private List<MedioDeNotificacion> mediosDeNotificacion ;

    public Contacto(String nombre, String apellido, String email, Double telefono, List<MedioDeNotificacion> mediosDeNotificacion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.mediosDeNotificacion = mediosDeNotificacion;
    }
}
