package domain.DuenioSuscripcion;

import domain.PreguntasAdopcion.PreguntaAdopcion;
import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.PreguntasAdopcion.TipoPregunta;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeAdoptantes;
import domain.autenticacion.Usuario;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;
import domain.organizacion.PublicacionAdoptante;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class suscripcionAdoptante {
    private static DAO<PublicacionAdoptante> dao= new DAOHibernate<>(PublicacionAdoptante.class);
    private static RepositorioDeAdoptantes repo=new RepositorioDeAdoptantes(dao);

    Duenio raul=new Duenio(null,null);
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
        //System.out.println(raul.getId());
        //System.out.println(carlos.getId());
        raul.desuscribirseARecomendacionesDeAdopcion();
    }
}
