package domain.Repositorios.factories;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioImagenes;
import domain.entities.fotos.Foto;

public class FactoryRepositorioImagenes {
    private static RepositorioImagenes repo;

    static {
        repo = null;
    }

    public static RepositorioImagenes get(){
        if(repo == null){
            DAO<Foto> dao = new DAOHibernate<>(Foto.class);
            repo = new RepositorioImagenes(dao);
        }
        return repo;
    }
}
