package domain.entities.notificacion;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import domain.entities.entidadPersistente.EntidadPersistente;
@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class FormaDeNotificacion extends EntidadPersistente {

     void notificar(Integer telefono,String mensaje){};

}
