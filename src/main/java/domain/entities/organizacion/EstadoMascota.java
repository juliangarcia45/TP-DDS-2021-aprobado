package domain.entities.organizacion;

import domain.entities.entidadPersistente.EntidadPersistente;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
public abstract class EstadoMascota extends EntidadPersistente {
    public abstract void mostrarEstado();
}
