package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.Duenio;

public class RepositorioDeDuenios extends Repositorio<Duenio>{
    public RepositorioDeDuenios(DAO<Duenio> dao) {
        super(dao);
    }
}
