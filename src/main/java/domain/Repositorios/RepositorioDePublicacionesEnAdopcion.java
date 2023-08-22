package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDePublicacionesEnAdopcion extends Repositorio<PublicacionMascotaEnAdopcion>{
    public RepositorioDePublicacionesEnAdopcion(DAO<PublicacionMascotaEnAdopcion> dao) {
        super(dao);
    }

    public List<PublicacionMascotaEnAdopcion> buscarTodasLasPublicaciones(){
        return this.dao.buscarTodos();
    }

    public List<PublicacionMascotaEnAdopcion> buscarPorEstado(EstadoPublicacion estado){
        return this.dao.buscarVarios(condicionTipoPublicacion(estado));
    }

    private BusquedaCondicional condicionTipoPublicacion(EstadoPublicacion estado){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<PublicacionMascotaEnAdopcion> publicacionQuery = criteriaBuilder.createQuery(PublicacionMascotaEnAdopcion.class);

        Root<PublicacionMascotaEnAdopcion> condicionRaiz = publicacionQuery.from(PublicacionMascotaEnAdopcion.class);

        Predicate condicionEstadoPublicacion = criteriaBuilder.equal(condicionRaiz.get("estado"), estado);

        publicacionQuery.where(condicionEstadoPublicacion);

        return new BusquedaCondicional(null, publicacionQuery);
    }
}
