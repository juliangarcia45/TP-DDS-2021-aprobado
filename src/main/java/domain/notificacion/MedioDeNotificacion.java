package domain.notificacion;

import domain.entidadPersistente.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="mediodenotificacion")
public class MedioDeNotificacion extends EntidadPersistente {

    @ManyToOne
    @JoinColumn(name="contactoANotificar_id", referencedColumnName = "id")
    private Contacto contactoANotificar;

    @Transient
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
