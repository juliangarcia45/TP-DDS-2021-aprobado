package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.fotos.Foto;

public class RepositorioImagenes extends Repositorio<Foto>{
    public RepositorioImagenes(DAO<Foto> dao) {
        super(dao);
    }
}
