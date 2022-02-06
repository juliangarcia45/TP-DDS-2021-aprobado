package domain.Repositorios.factories;

import config.Config;
import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicacionesEnAdopcion;
import domain.entities.organizacion.PublicacionMascotaEnAdopcion;

public class factoryRepositorioPublicacionesEnAdopcion {
        private static RepositorioDePublicacionesEnAdopcion repo;

        static {
            repo = null;
        }

        public static RepositorioDePublicacionesEnAdopcion get(){
            if(repo == null){
                if(Config.useDataBase){
                    DAO<PublicacionMascotaEnAdopcion> dao = new DAOHibernate<>(PublicacionMascotaEnAdopcion.class);
                    repo = new RepositorioDePublicacionesEnAdopcion(dao);
                }
            }
            return repo;
        }
    }

