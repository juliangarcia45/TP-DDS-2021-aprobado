package domain.publicaciones;

import domain.PreguntasAdopcion.PreguntaAdopcion;
import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.PreguntasAdopcion.TipoPregunta;
import domain.RepositorioAdoptantes.RepositorioAdoptantes;
import domain.organizacion.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class obtenerRecomendacionTest {
    PreguntaAdopcion pregunta= new PreguntaAdopcion("a", TipoPregunta.SINGLE);
    RespuestaAdopcion respuesta= new RespuestaAdopcion(pregunta,null);
    Organizacion patitas = new Organizacion();
    Duenio raul=new Duenio(null,null);
    Mascota mascotahh = new Mascota.MascotaBuilder(null,null).nombre("gato").apodo("jhol").build();
    PublicacionMascotaEnAdopcion oka = new PublicacionMascotaEnAdopcion(null, mascotahh);
    PublicacionMascotaEnAdopcion koro = new PublicacionMascotaEnAdopcion(null, mascotahh);
    PublicacionAdoptante as=new PublicacionAdoptante(raul,new ArrayList<>());
    List<Publicacion> publicacionesEsp=new ArrayList<>();

    @Before
    public void initPublicaciones() throws IOException {
        as.agregarRespuesta(respuesta);

        oka.agregarRespuesta(respuesta);



        oka.aprobarPublicacion();
        koro.aprobarPublicacion();

        patitas.addPublicacion(oka);
        patitas.addPublicacion(koro);

        publicacionesEsp.add(oka);

        RepositorioAdoptantes.agregarInteresado(as);
        RepositorioOrganizaciones.agregarOrganizacion(patitas);
    }
    @Test
    public void obtenerRecomen() throws IOException {
        assertEquals(publicacionesEsp,Recomendacion.obtenerRecomendacion(as));
    }
}
