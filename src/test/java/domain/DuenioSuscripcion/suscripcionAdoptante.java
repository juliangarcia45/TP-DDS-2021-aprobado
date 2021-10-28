package domain.DuenioSuscripcion;

import domain.entities.PreguntasAdopcion.PreguntaAdopcion;
import domain.entities.PreguntasAdopcion.RespuestaAdopcion;
import domain.entities.PreguntasAdopcion.TipoPregunta;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeAdoptantes;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.PublicacionAdoptante;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class suscripcionAdoptante {
    private static DAO<PublicacionAdoptante> dao= new DAOHibernate<>(PublicacionAdoptante.class);
    private static RepositorioDeAdoptantes repo=new RepositorioDeAdoptantes(dao);

    Duenio raul=new Duenio("sdsadasasd",null);
    Duenio carlos=new Duenio(null,null);
    List<RespuestaAdopcion> preferencias=new ArrayList<>();

    @Before
    public void initAdoptante() throws IOException {
        PreguntaAdopcion pregunta=new PreguntaAdopcion("??", TipoPregunta.SINGLE);
        pregunta.agregarOpcion("as");
        RespuestaAdopcion respuesta=new RespuestaAdopcion(pregunta,new ArrayList<>());
        respuesta.agregarValor("a");
        preferencias.add(respuesta);
    }

    @Test
    public void suscribirAdoptante() throws IOException {
        raul.suscribirseARecomendacionesDeAdopcion(preferencias);
    }

    @Test
    public void desuscribirAdoptante() throws IOException {
        raul.desuscribirseARecomendacionesDeAdopcion();
    }
}
