package domain.entities.organizacion;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeOrganizaciones;

import java.util.List;
import java.util.stream.Collectors;

public class gestorOrganizaciones {
    private static DAO<Organizacion> dao= new DAOHibernate<>(Organizacion.class);
    private static RepositorioDeOrganizaciones repo=new RepositorioDeOrganizaciones(dao);

    protected static Organizacion obtenerOrgMasCercana(Ubicacion ubicacion){
        List<Ubicacion> ubicacionesOrg=repo.buscarTodasLasOrganizaciones().stream().map(Organizacion -> Organizacion.getUbicacion()).collect(Collectors.toList());
        int index=ubicacion.distanciaMasCortaA(ubicacionesOrg);
        return repo.buscarTodasLasOrganizaciones().get(index);
    }
}
