package domain.entities.organizacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("en_adopcion")
public class MascotaEnAdopcion extends EstadoMascota {
    public MascotaEnAdopcion(){}

    @Override
    public void mostrarEstado() {
        System.out.println("Mascota en adopcion");
    }
}
