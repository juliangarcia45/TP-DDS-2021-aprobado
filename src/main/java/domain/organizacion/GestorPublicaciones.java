package domain.organizacion;


import domain.autenticacion.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class GestorPublicaciones {

    protected static Organizacion obtenerOrgMasCercana(Ubicacion ubicacion){
        List<Ubicacion> ubicacionesOrg=RepositorioOrganizaciones.getOrganizaciones().stream().map(Organizacion -> Organizacion.getUbicacion()).collect(Collectors.toList());
        int index=ubicacion.distanciaMasCortaA(ubicacionesOrg);
        return RepositorioOrganizaciones.getOrganizaciones().get(index);
    }

//VER ACA CON TEST OBTENERMASCOTASPERDIDASTEST
    public static List<Publicacion> obtenerPublicacionesMascPerdidas(){
        List<Publicacion> publicaciones=new ArrayList<>();
       RepositorioOrganizaciones.getOrganizaciones().stream().forEach(Organizacion -> Organizacion.publicacionesDeMascotasPerdidas().forEach(Publicacion -> publicaciones.add(Publicacion)));
        return publicaciones;
    }

    public static List<Publicacion> obtenerPublicacionesMascEnAdopcion(){
        List<Publicacion> publicaciones=new ArrayList<>();
        RepositorioOrganizaciones.getOrganizaciones().stream().forEach(Organizacion -> Organizacion.publicacionesDeMascotasEnAdopcion().forEach(Publicacion -> publicaciones.add(Publicacion)));
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
