package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeDuenios;
import domain.entities.organizacion.Duenio;

public class FactoryRepositorioDuenio {
    private static RepositorioDeDuenios repo;

    static {
        repo = null;
    }

    public static RepositorioDeDuenios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Duenio> dao = new DAOHibernate<>(Duenio.class);
                repo = new RepositorioDeDuenios(dao);
            }
        }
        return repo;
    }
}
