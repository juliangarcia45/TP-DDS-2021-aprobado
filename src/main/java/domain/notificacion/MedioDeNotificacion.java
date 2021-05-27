package domain.notificacion;


public class MedioDeNotificacion  {
    private Contacto contactoANotificar;
    private FormaDeNotificacion estrategiaNotificacion;

    public void cambiarFormaDeNotificacion(FormaDeNotificacion formaDeNotificacion){

    }
    public void notificar(Integer telefono,String mensaje){
    this.estrategiaNotificacion.notificar(telefono,mensaje);
    }

    public FormaDeNotificacion getEstrategiaNotificacion() {
        return estrategiaNotificacion;
    }

    public void setEstrategiaNotificacion(FormaDeNotificacion estrategiaNotificacion) {
        this.estrategiaNotificacion = estrategiaNotificacion;
    }
}
