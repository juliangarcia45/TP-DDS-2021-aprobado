package domain;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;
import org.junit.Test;


import static org.junit.Assert.*;

public class registrarMascotaTest {
    @Test
    public void registrarMascota() {
        Duenio jorge= new Duenio("jorge", "jorge");
        Mascota mascota = new Mascota("gato",true,null,null,null,true,null,jorge,null);
        jorge.registrarMascota(mascota);
        assertEquals(mascota,jorge.getMascotas().get(0));

    }
}

