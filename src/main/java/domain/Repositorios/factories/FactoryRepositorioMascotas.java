package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeMascotas;
import domain.entities.organizacion.Mascota;

public class FactoryRepositorioMascotas {
    private static RepositorioDeMascotas repo;

    static {
        repo = null;
    }

    public static RepositorioDeMascotas get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Mascota> dao = new DAOHibernate<>(Mascota.class);
                repo = new RepositorioDeMascotas(dao);
            }
        }
        return repo;
    }
}
