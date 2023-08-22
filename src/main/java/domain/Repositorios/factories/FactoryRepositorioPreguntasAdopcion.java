package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePreguntasAdopcion;
import domain.entities.PreguntasAdopcion.PreguntaAdopcion;

public class FactoryRepositorioPreguntasAdopcion {
    private static RepositorioDePreguntasAdopcion repo;

    static {
        repo = null;
    }

    public static RepositorioDePreguntasAdopcion get(){
        if(repo == null){
            if(Config.useDataBase){
                DAO<PreguntaAdopcion> dao = new DAOHibernate<>(PreguntaAdopcion.class);
                repo = new RepositorioDePreguntasAdopcion(dao);
            }
        }
        return repo;
    }
}
