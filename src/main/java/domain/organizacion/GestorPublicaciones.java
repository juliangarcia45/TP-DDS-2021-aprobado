package domain.organizacion;

import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeOrganizaciones;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GestorPublicaciones {
    private static DAO<Organizacion> dao= new DAOHibernate<>(Organizacion.class);
    private static RepositorioDeOrganizaciones repo=new RepositorioDeOrganizaciones(dao);

    protected static Organizacion obtenerOrgMasCercana(Ubicacion ubicacion){
        List<Ubicacion> ubicacionesOrg=repo.buscarTodasLasOrganizaciones().stream().map(Organizacion -> Organizacion.getUbicacion()).collect(Collectors.toList());
        int index=ubicacion.distanciaMasCortaA(ubicacionesOrg);
        return repo.buscarTodasLasOrganizaciones().get(index);
    }

//VER ACA CON TEST OBTENERMASCOTASPERDIDASTEST
    public static List<Publicacion> obtenerPublicacionesMascPerdidas(){
        List<Publicacion> publicaciones=new ArrayList<>();
        repo.buscarTodasLasOrganizaciones().stream().forEach(Organizacion -> Organizacion.publicacionesDeMascotasPerdidas().forEach(Publicacion -> publicaciones.add(Publicacion)));
        return publicaciones;
    }

    public static List<Publicacion> obtenerPublicacionesMascEnAdopcion(){
        List<Publicacion> publicaciones=new ArrayList<>();
        repo.buscarTodasLasOrganizaciones().stream().forEach(Organizacion -> Organizacion.publicacionesDeMascotasEnAdopcion().forEach(Publicacion -> publicaciones.add(Publicacion)));
        return publicaciones;
    }

    public static void generarPublicacionMascotaPerdida(Rescatista rescatista, String estadoMascota, List<String> fotosMascota) {
        Organizacion org= obtenerOrgMasCercana(rescatista.getDireccionEncuentroMascota());
        org.getListaPublicaciones().add(new PublicacionMascotaPerdida(rescatista,fotosMascota,estadoMascota));
    }

    public static void generarPublicacionMascotaEnAdopcion(Duenio duenio, Mascota mascota, Organizacion organizacion) {
        organizacion.getListaPublicaciones().add(new PublicacionMascotaEnAdopcion(duenio,mascota));
    }
}
