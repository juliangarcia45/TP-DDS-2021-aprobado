package domain.Repositorios.factories;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.config.Config;
import domain.organizacion.Publicacion;

public class FactoryRepositorioPublicaciones {
    private static RepositorioDePublicaciones repo;

    static {
        repo = null;
    }

    public static RepositorioDePublicaciones get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Publicacion> dao = new DAOHibernate<>(Publicacion.class);
                repo = new RepositorioDePublicaciones(dao);
            }
        }
        return repo;
    }
}
