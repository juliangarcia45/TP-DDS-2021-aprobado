package domain.entities.organizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("perdida")
public class MascotaPerdida extends EstadoMascota {
    public MascotaPerdida(){}

    @Override
    public void mostrarEstado() {
        System.out.println("Mascota perdida");
    }

}
