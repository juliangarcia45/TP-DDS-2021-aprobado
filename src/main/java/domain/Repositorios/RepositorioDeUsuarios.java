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
    public Boolean existe(String nombreDeUsuario, String contrasenia){
        return buscarUsuarioExistente(nombreDeUsuario, contrasenia) != null;
    }
    public Boolean estaRegistradoBoolean(String nombreUsuario){
    
        List<Usuario> lista= this.dao.buscarVarios(condicionUsername(nombreUsuario));
        return !lista.isEmpty();
    }
    public void agregar(Usuario unUsuario){
        this.dao.agregar(unUsuario);
    }

    public void modificar(Usuario unUsuario){
        this.dao.modificar(unUsuario);
    }

    public void eliminar(Usuario unUsuario){
        this.dao.eliminar(unUsuario);
    }

    public List<Usuario> buscarTodosLosUsuarios(){
        return this.dao.buscarTodos();
    }
    public Usuario buscarUsuario(String usuario){
        return this.dao.buscar(condicionUsername(usuario));
    }
    
    public Usuario buscarUsuarioExistente(String nombreDeUsuario, String contrasenia){
        return this.dao.buscar(condicionUsuarioYContrasenia(nombreDeUsuario,contrasenia));
    }

    private BusquedaCondicional condicionUsuarioYContrasenia(String nombreDeUsuario, String contrasenia){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionNombreDeUsuario = criteriaBuilder.equal(condicionRaiz.get("usuario"), nombreDeUsuario);
        Predicate condicionContrasenia = criteriaBuilder.equal(condicionRaiz.get("contrasenia"), contrasenia);

        Predicate condicionExisteUsuario = criteriaBuilder.and(condicionNombreDeUsuario, condicionContrasenia);

        usuarioQuery.where(condicionExisteUsuario);

        return new BusquedaCondicional(null, usuarioQuery);
    }

    private BusquedaCondicional condicionUsername(String usuario){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Usuario> usuarioQuery = criteriaBuilder.createQuery(Usuario.class);

        Root<Usuario> condicionRaiz = usuarioQuery.from(Usuario.class);

        Predicate condicionUsername = criteriaBuilder.equal(condicionRaiz.get("usuario"), usuario);


        usuarioQuery.where(condicionUsername);

        return new BusquedaCondicional(null, usuarioQuery);
    }
}
