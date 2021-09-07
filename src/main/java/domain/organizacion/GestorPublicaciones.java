package domain.organizacion;


import domain.autenticacion.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GestorPublicaciones {

    private static final GestorPublicaciones instance = new GestorPublicaciones();
    private static List<Organizacion> organizaciones= new ArrayList<>();

    protected static Organizacion obtenerOrgMasCercana(Ubicacion ubicacion){
        List<Ubicacion> ubicacionesOrg=organizaciones.stream().map(Organizacion -> Organizacion.getUbicacion()).collect(Collectors.toList());
        int index=ubicacion.distanciaMasCortaA(ubicacionesOrg);
        return organizaciones.get(index);
    }


    public static GestorPublicaciones getInstance() {
        return instance;
    }
//VER ACA CON TEST OBTENERMASCOTASPERDIDASTEST
    public static List<Publicacion> obtenerPublicacionesMascPerdidas(){
        List<Publicacion> publicaciones=new ArrayList<>();
       organizaciones.stream().forEach(Organizacion -> Organizacion.publicacionesDeMascotasPerdidas().forEach(Publicacion -> publicaciones.add(Publicacion)));
        return publicaciones;
    }

    public static List<Publicacion> obtenerPublicacionesMascEnAdopcion(){
        List<Publicacion> publicaciones=new ArrayList<>();
        organizaciones.stream().forEach(Organizacion -> Organizacion.publicacionesDeMascotasEnAdopcion().forEach(Publicacion -> publicaciones.add(Publicacion)));
        return publicaciones;
    }

    public static void generarPublicacionMascotaPerdida(Rescatista rescatista, String estadoMascota, List<String> fotosMascota) {
        Organizacion org= obtenerOrgMasCercana(rescatista.getDireccionEncuentroMascota());
        org.getListaPublicaciones().add(new PublicacionMascotaPerdida(rescatista,fotosMascota,estadoMascota));
    }

    public static void generarPublicacionMascotaEnAdopcion(Duenio duenio, Mascota mascota, Organizacion organizacion) {
        organizacion.getListaPublicaciones().add(new PublicacionMascotaEnAdopcion(duenio,mascota));
    }



    public static void agregarOrganizacion(Organizacion organizacion){
        organizaciones.add(organizacion);
    }
}
