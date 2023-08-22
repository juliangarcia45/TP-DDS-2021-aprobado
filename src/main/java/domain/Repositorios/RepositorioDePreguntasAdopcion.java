package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.PreguntasAdopcion.PreguntaAdopcion;

public class RepositorioDePreguntasAdopcion extends Repositorio<PreguntaAdopcion> {
    public RepositorioDePreguntasAdopcion(DAO<PreguntaAdopcion> dao) {
        super(dao);
    }
}
