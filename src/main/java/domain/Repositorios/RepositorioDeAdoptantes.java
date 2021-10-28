package domain.Repositorios;

import domain.entities.organizacion.PublicacionAdoptante;
import domain.Repositorios.Daos.DAO;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeAdoptantes extends Repositorio<PublicacionAdoptante> {


    public RepositorioDeAdoptantes(DAO<PublicacionAdoptante> dao) {
        super(dao);
    }

    public void agregar(PublicacionAdoptante interesado){
        this.dao.agregar(interesado);
    }

    public void eliminar(PublicacionAdoptante interesado){
        this.dao.eliminar(interesado);
    }


    public List<PublicacionAdoptante> buscarTodosLosAdoptantes(){
        return this.dao.buscarTodos();
    }

    public PublicacionAdoptante buscarInteresado(int idInteresado){
        return this.dao.buscar(condicionIdInteresado(idInteresado));
    }

    private BusquedaCondicional condicionIdInteresado(int idInteresado){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<PublicacionAdoptante> interesadoQuery = criteriaBuilder.createQuery(PublicacionAdoptante.class);

        Root<PublicacionAdoptante> condicionRaiz = interesadoQuery.from(PublicacionAdoptante.class);

        Predicate condicionIdInteresado = criteriaBuilder.equal(condicionRaiz.get("interesado"), idInteresado);

        Predicate condicionExisteInteresado = criteriaBuilder.and(condicionIdInteresado);

        interesadoQuery.where(condicionExisteInteresado);

        return new BusquedaCondicional(null, interesadoQuery);
    }
}
