package domain.Repositorios;

import domain.entities.organizacion.Organizacion;
import domain.Repositorios.Daos.DAO;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeOrganizaciones extends Repositorio<Organizacion> {


    public RepositorioDeOrganizaciones(DAO<Organizacion> dao) {
        super(dao);
    }

    public Boolean existe(String nombreDeOrganizacion){
        return buscarOrganizacion(nombreDeOrganizacion) != null;
    }

    public List<Organizacion> buscarTodasLasOrganizaciones(){
        return this.dao.buscarTodos();
    }

    public Organizacion buscarOrganizacion(String nombreDeOrganizacion){
        return this.dao.buscar(condicionNombreOrganizacion(nombreDeOrganizacion));
    }

    private BusquedaCondicional condicionNombreOrganizacion(String nombreDeOrganizacion){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Organizacion> organizacionQuery = criteriaBuilder.createQuery(Organizacion.class);

        Root<Organizacion> condicionRaiz = organizacionQuery.from(Organizacion.class);

        Predicate condicionNombreDeOrganizacion = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombreDeOrganizacion);

        Predicate condicionExisteOrganizacion = criteriaBuilder.and(condicionNombreDeOrganizacion);

        organizacionQuery.where(condicionExisteOrganizacion);

        return new BusquedaCondicional(null, organizacionQuery);
    }
}
