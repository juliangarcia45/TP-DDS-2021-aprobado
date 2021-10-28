package domain.publicaciones;


import bd.BDUtils.EntityManagerHelper;
import domain.entities.organizacion.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class obtenerPublicacionesMascotasPerdidasTest {
    Organizacion patitas = new Organizacion();
    Organizacion patitasDos = new Organizacion();
    List<Publicacion> publicaciones;
    Rescatista clark = new Rescatista("clark", "xD");
    Duenio aaa = new Duenio(null,null);


    @Before
    public void initPublicaciones() throws IOException{
        Publicacion donPepe = new PublicacionMascotaPerdida(clark, null, null);
        
        Publicacion donJonny = new PublicacionMascotaPerdida(clark, null, null);

        Publicacion yuumi = new PublicacionMascotaPerdida(clark, null, null);

        donPepe.aprobarPublicacion();
        yuumi.aprobarPublicacion();


        publicaciones = new ArrayList<>();
        publicaciones.add(yuumi);
        publicaciones.add(donPepe);

        patitas.addPublicacion(donJonny);
        patitas.addPublicacion(yuumi);
        patitasDos.addPublicacion(donPepe);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(patitas);
        EntityManagerHelper.getEntityManager().persist(patitasDos);
        EntityManagerHelper.commit();
        

    }

    @Test
    public void filtrarMascotasPerdidas() throws IOException {

        assertEquals(publicaciones,GestorPublicaciones.obtenerPublicacionesMascPerdidas());
    }

}

