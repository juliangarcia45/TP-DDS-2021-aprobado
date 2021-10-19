package domain.Repositorios;

import domain.organizacion.Organizacion;
import domain.organizacion.PublicacionAdoptante;
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

    //Falta un buscarAdoptante para desuscribirse en Duenio


}
