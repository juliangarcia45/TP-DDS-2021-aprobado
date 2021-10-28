package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.EstadoPublicacion;
import domain.entities.organizacion.Publicacion;
import domain.entities.organizacion.TipoPublicacion;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDePublicaciones extends Repositorio<Publicacion> {
    public RepositorioDePublicaciones(DAO<Publicacion> dao) {
        super(dao);
    }

    public List<Publicacion> buscarTodasLasPublicaciones(){
        return this.dao.buscarTodos();
    }

    public List<Publicacion> buscarPorTipo(TipoPublicacion tipo,EstadoPublicacion estado){
        return this.dao.buscarVarios(condicionTipoPublicacion(tipo, estado));
    }

    private BusquedaCondicional condicionTipoPublicacion(TipoPublicacion tipo, EstadoPublicacion estado){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Publicacion> publicacionQuery = criteriaBuilder.createQuery(Publicacion.class);

        Root<Publicacion> condicionRaiz = publicacionQuery.from(Publicacion.class);

        Predicate condicionTipoDePublicacion = criteriaBuilder.equal(condicionRaiz.get("tipo"), tipo);
        Predicate condicionEstadoPublicacion = criteriaBuilder.equal(condicionRaiz.get("estado"), estado);

        Predicate condicionExistenPublicaciones = criteriaBuilder.and(condicionTipoDePublicacion,condicionEstadoPublicacion);

        publicacionQuery.where(condicionExistenPublicaciones);

        return new BusquedaCondicional(null, publicacionQuery);
    }
}
