package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicacionesMascPerdidas;
import domain.entities.organizacion.PublicacionMascotaPerdida;

public class FactoryRepositorioPublicacionesMascPerdida {
    private static RepositorioDePublicacionesMascPerdidas repo;

    static {
        repo = null;
    }

    public static RepositorioDePublicacionesMascPerdidas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<PublicacionMascotaPerdida> dao = new DAOHibernate<>(PublicacionMascotaPerdida.class);
                repo = new RepositorioDePublicacionesMascPerdidas(dao);
            }
        }
        return repo;
    }
}
