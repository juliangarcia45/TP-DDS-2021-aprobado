package domain.publicaciones;


import bd.BDUtils.EntityManagerHelper;
import domain.entities.organizacion.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class obtenerPublicacionesMascotasEnAdopcionTest {
    Organizacion patitas = new Organizacion();
    List<Publicacion> publicaciones;
    Rescatista clark = new Rescatista("clark", "xD");
    Mascota calli=new Mascota.MascotaBuilder(null,"a").apodo("calli").edad(14).build();
    @Before
    public void initPublicaciones() throws IOException{
        Publicacion donPepe = new PublicacionMascotaPerdida(clark, null, null);
        
        Publicacion donJonny = new PublicacionMascotaPerdida(clark, null, null);

        Publicacion yuumi = new PublicacionMascotaEnAdopcion(null, calli);

        donPepe.aprobarPublicacion();
        yuumi.aprobarPublicacion();

        publicaciones = new ArrayList<>();
        publicaciones.add(yuumi);

        patitas.addPublicacion(donJonny);
        patitas.addPublicacion(yuumi);
        patitas.addPublicacion(donPepe);

        EntityManagerHelper.beginTransaction();

        EntityManagerHelper.getEntityManager().persist(calli);
        EntityManagerHelper.getEntityManager().persist(patitas);
        EntityManagerHelper.commit();



    }

    @Test
    public void filtrarEnAdopcion() throws IOException {
        assertEquals(publicaciones, GestorPublicaciones.obtenerPublicacionesMascEnAdopcion());
    }
}
