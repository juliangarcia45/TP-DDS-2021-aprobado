package domain.Repositorios.factories;

import config.Config;
import domain.entities.organizacion.Organizacion;
import domain.Repositorios.RepositorioDeOrganizaciones;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;


public class FactoryRepositorioOrganizaciones {
    private static RepositorioDeOrganizaciones repo;

    static {
        repo = null;
    }

    public static RepositorioDeOrganizaciones get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Organizacion> dao = new DAOHibernate<>(Organizacion.class);
                repo = new RepositorioDeOrganizaciones(dao);
            }
        }
        return repo;
    }
}
