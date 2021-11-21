package domain.entities.notificacion;

public interface Contactable {
    String getEmail();
    void setEmail(String email);
    String getTelefono();
    void setTelefono(String telefono);
    void setMensaje(String mensaje);
    String getMensaje();
}
