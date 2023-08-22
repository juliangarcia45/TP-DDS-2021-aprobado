package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.*;
import domain.entities.organizacion.PublicacionMascotaPerdida;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDePublicacionesMascPerdidas extends Repositorio<PublicacionMascotaPerdida> {
    public RepositorioDePublicacionesMascPerdidas(DAO<PublicacionMascotaPerdida> dao) {
        super(dao);
    }

    public List<PublicacionMascotaPerdida> buscarTodasLasPublicaciones(){
        return this.dao.buscarTodos();
    }

    public List<PublicacionMascotaPerdida> buscarPorEstado(EstadoPublicacion estado){
        return this.dao.buscarVarios(condicionTipoPublicacion(estado));
    }

    private BusquedaCondicional condicionTipoPublicacion(EstadoPublicacion estado){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<PublicacionMascotaPerdida> publicacionQuery = criteriaBuilder.createQuery(PublicacionMascotaPerdida.class);

        Root<PublicacionMascotaPerdida> condicionRaiz = publicacionQuery.from(PublicacionMascotaPerdida.class);

        Predicate condicionEstadoPublicacion = criteriaBuilder.equal(condicionRaiz.get("estado"), estado);

        publicacionQuery.where(condicionEstadoPublicacion);

        return new BusquedaCondicional(null, publicacionQuery);
    }

}
