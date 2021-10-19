package domain.Repositorios.factories;

import domain.config.Config;
import domain.Repositorios.RepositorioDeAdoptantes;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.organizacion.PublicacionAdoptante;


public class FactoryRepositorioAdoptantes {
    private static RepositorioDeAdoptantes repo;

    static {
        repo = null;
    }

    public static RepositorioDeAdoptantes get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<PublicacionAdoptante> dao = new DAOHibernate<>(PublicacionAdoptante.class);
                repo = new RepositorioDeAdoptantes(dao);
            }
        }
        return repo;
    }
}
