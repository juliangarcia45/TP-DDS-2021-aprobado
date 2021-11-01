package domain.entities.notificacion;

import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.*;

@Entity
@Table(name="mediodenotificacion")
public class MedioDeNotificacion extends EntidadPersistente {



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "estrategia_notificacion")
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

    public MedioDeNotificacion() {
    }
}
