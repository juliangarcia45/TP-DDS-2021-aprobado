package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Repositorio;
import domain.Repositorios.Daos.*;

import java.util.HashMap;

public class FactoryRepositorio {
    private static HashMap<String, Repositorio> repos;

    static {
        repos = new HashMap<>();
    }

    public static <T> Repositorio<T> get(Class<T> type){
        Repositorio<T> repo=null;
        if(repos.containsKey(type.getName())){
            repo = repos.get(type.getName());
        }
        else{
            if(Config.useDataBase){
                DAO<T> dao = new DAOHibernate<>(type);
                repo = new Repositorio<>(dao);
            }
            repos.put(type.toString(), repo);
        }
        return repo;
    }
}
