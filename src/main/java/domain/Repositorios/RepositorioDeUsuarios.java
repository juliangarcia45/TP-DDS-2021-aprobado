package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.autenticacion.Usuario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeUsuarios extends Repositorio<Usuario>{
    public RepositorioDeUsuarios(DAO<Usuario> dao) {
        super(dao);
    }

    public List<Usuario> buscarTodosLosUsuarios(){
        return this.dao.buscarTodos();
    }

    public Usuario buscarUsuario(String usuario){
        return this.dao.buscar(condicionUsername(usuario));
    }

    private BusquedaCondicional condicionUsername(String usuario){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionUsername = criteriaBuilder.equal(condicionRaiz.get("usuario"), usuario);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionUsername);

        usuarioQuery.where(condicionUsername);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}
