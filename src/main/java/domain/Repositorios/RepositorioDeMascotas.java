package domain.Repositorios;

import domain.Repositorios.Daos.DAO;
import domain.entities.organizacion.Mascota;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class RepositorioDeMascotas extends Repositorio<Mascota> {
    public RepositorioDeMascotas(DAO<Mascota> dao) {
        super(dao);
    }

    public Boolean existe(String nombre){
        return buscarMascota(nombre) != null;
    }
    public void agregar(Mascota unaMascota){
        this.dao.agregar(unaMascota);
    }

    public void modificar(Mascota unaMascota){
        this.dao.modificar(unaMascota);
    }

    public void eliminar(Mascota unaMascota){
        this.dao.eliminar(unaMascota);
    }

    public List<Mascota> buscarTodasLasMascotas(){
        return this.dao.buscarTodos();
    }

    public Mascota buscarMascota(String nombre){
        return this.dao.buscar(condicionNombre(nombre));
    }

    private BusquedaCondicional condicionNombre(String nombre){
        CriteriaBuilder criteriaBuilder = criteriaBuilder();
        CriteriaQuery<Mascota> mascotaQuery = criteriaBuilder.createQuery(Mascota.class);

        Root<Mascota> condicionRaiz = mascotaQuery.from(Mascota.class);

        Predicate condicionNombreMascota = criteriaBuilder.equal(condicionRaiz.get("nombre"), nombre);


        mascotaQuery.where(condicionNombreMascota);

        return new BusquedaCondicional(null, mascotaQuery);
    }
}
