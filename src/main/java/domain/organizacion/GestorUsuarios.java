package domain.organizacion;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicaciones;
import domain.Repositorios.RepositorioDeUsuarios;
import domain.autenticacion.Usuario;

public abstract class GestorUsuarios {
    private static DAO<Usuario> dao= new DAOHibernate<>(Usuario.class);
    private static RepositorioDeUsuarios repo=new RepositorioDeUsuarios(dao);

    public static Usuario obtenerUsuario(String username){
        return repo.buscarUsuario(username);
    }


}
