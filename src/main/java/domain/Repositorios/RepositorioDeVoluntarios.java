package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.Voluntario;

public class RepositorioDeVoluntarios extends Repositorio<Voluntario> {
    public RepositorioDeVoluntarios(DAO<Voluntario> dao) {
        super(dao);
    }
}
