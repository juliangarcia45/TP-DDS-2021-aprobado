package domain.publicaciones;


import domain.organizacion.Organizacion;
import domain.organizacion.Publicacion;
import domain.organizacion.PublicacionMascotaEnAdopcion;
import domain.organizacion.PublicacionMascotaPerdida;
import domain.organizacion.Rescatista;

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
    @Before
    public void initPublicaciones() throws IOException{
        Publicacion donPepe = new PublicacionMascotaPerdida(clark, null, null);
        
        Publicacion donJonny = new PublicacionMascotaPerdida(clark, null, null);

        Publicacion yuumi = new PublicacionMascotaEnAdopcion(null, null, null);

        donPepe.aprobarPublicacion();
        yuumi.aprobarPublicacion();

        publicaciones = new ArrayList<>();
        publicaciones.add(yuumi);

        patitas.addPublicacion(donJonny);
        patitas.addPublicacion(yuumi);
        patitas.addPublicacion(donPepe);
    }

    @Test
    public void filtrarEnAdopcion() throws IOException {

        assertEquals(publicaciones,patitas.publicacionesDeMascotasEnAdopcion());
    }
}
