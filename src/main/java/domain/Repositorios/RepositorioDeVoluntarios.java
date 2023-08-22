package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.Organizacion;
import domain.entities.organizacion.Voluntario;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeVoluntarios extends Repositorio<Voluntario> {
    public RepositorioDeVoluntarios(DAO<Voluntario> dao) {
        super(dao);
    }

    public List<Voluntario> buscarPorOrganizacion(Organizacion organizacion){
        return this.dao.buscarVarios(condicionOrganizacion(organizacion));
    }

    private BusquedaCondicional condicionOrganizacion(Organizacion organizacion){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Voluntario> organizacionQuery = criteriaBuilder.createQuery(Voluntario.class);

        Root<Voluntario> condicionRaiz = organizacionQuery.from(Voluntario.class);

        Predicate condicionOrganizacion = criteriaBuilder.equal(condicionRaiz.get("organizacion"), organizacion);

        organizacionQuery.where(condicionOrganizacion);

        return new BusquedaCondicional(null, organizacionQuery);
    }
}
