package domain.entities.organizacion;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDePublicaciones;


import java.util.List;

public abstract class GestorPublicaciones {
    private static DAO<Publicacion> dao= new DAOHibernate<>(Publicacion.class);
    private static RepositorioDePublicaciones repo=new RepositorioDePublicaciones(dao);



//VER ACA CON TEST OBTENERMASCOTASPERDIDASTEST
    public static List<Publicacion> obtenerPublicacionesMascPerdidas(){
        return repo.buscarPorTipo(TipoPublicacion.PERDIDA,EstadoPublicacion.APROBADO);
    }

    public static List<Publicacion> obtenerPublicacionesMascEnAdopcion(){
        return repo.buscarPorTipo(TipoPublicacion.EN_ADOPCION,EstadoPublicacion.APROBADO);
    }

    public static void generarPublicacionMascotaPerdida(Rescatista rescatista, String estadoMascota, List<String> fotosMascota) {
        Organizacion org= gestorOrganizaciones.obtenerOrgMasCercana(rescatista.getDireccionEncuentroMascota());
        org.getListaPublicaciones().add(new PublicacionMascotaPerdida(rescatista,fotosMascota,estadoMascota));
    }

    public static void generarPublicacionMascotaEnAdopcion(Duenio duenio, Mascota mascota, Organizacion organizacion) {
        organizacion.getListaPublicaciones().add(new PublicacionMascotaEnAdopcion(duenio,mascota));
    }
}
