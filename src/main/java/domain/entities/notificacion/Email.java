package domain.entities.notificacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("EMAIL")
public class Email extends FormaDeNotificacion{
    @Override
    public void notificar(Integer telefono,String mensaje){

    }
}
