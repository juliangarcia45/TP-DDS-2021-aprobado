package domain;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.Mascota;
import org.junit.Test;


import static org.junit.Assert.*;

public class registrarMascotaTest {
    @Test
    public void registrarMascota() {
        Duenio jorge= new Duenio("jorge", "jorge");
        //Mascota mascota = new Mascota("gato",true,null,null,null,true,null,jorge,null);
        Mascota mascota = new Mascota("nombre",null,"jhol",null,10,null,null,null,null,null);
        mascota.setDuenio(jorge);
        jorge.registrarMascota(mascota);
        assertEquals(mascota,jorge.getMascotas().get(0));

    }
}

