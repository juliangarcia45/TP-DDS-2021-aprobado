package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeMascotas;
import domain.Repositorios.RepositorioDeVoluntarios;
import domain.entities.organizacion.Mascota;
import domain.entities.organizacion.Voluntario;

public class FactoryRepositorioVoluntarios {
    private static RepositorioDeVoluntarios repo;

    static {
        repo = null;
    }

    public static RepositorioDeVoluntarios get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<Voluntario> dao = new DAOHibernate<>(Voluntario.class);
                repo = new RepositorioDeVoluntarios(dao);
            }
        }
        return repo;
    }
}
