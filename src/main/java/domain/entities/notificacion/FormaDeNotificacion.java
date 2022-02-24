package domain.entities.notificacion;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import domain.entities.entidadPersistente.EntidadPersistente;
import org.apache.commons.mail.EmailException;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class FormaDeNotificacion extends EntidadPersistente {

     void notificar(String telefono,String mensaje) throws EmailException {};

}
