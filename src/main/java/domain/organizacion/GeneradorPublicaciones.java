package domain.organizacion;


import domain.autenticacion.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneradorPublicaciones {

    private static final GeneradorPublicaciones instance = new GeneradorPublicaciones();
    private static List<Organizacion> organizaciones= new ArrayList<>();

    protected static Organizacion obtenerOrgMasCercana(Ubicacion ubicacion){
        List<Ubicacion> ubicacionesOrg=organizaciones.stream().map(Organizacion -> Organizacion.getUbicacion()).collect(Collectors.toList());
        int index=ubicacion.distanciaMasCortaA(ubicacionesOrg);
        return organizaciones.get(index);
    }


    public static GeneradorPublicaciones getInstance() {
        return instance;
    }
//VER ACA CON TEST OBTENERMASCOTASPERDIDASTEST
    public static List<Publicacion> obtenerPublicacionesMascPerdidas(){
        List<Publicacion> publicaciones=new ArrayList<>();
        List<List<Publicacion>> aux= organizaciones.stream().map(Organizacion -> Organizacion.publicacionesDeMascotasPerdidas()).collect(Collectors.toList());
        for(int i=0 ;i<= aux.size();i++){
            aux.get(i).forEach(Publicacion->publicaciones.add(Publicacion));
        }
        return publicaciones;
    }
    public static void generarPublicacionMascotaPerdida(Rescatista rescatista, String estadoMascota, List<String> fotosMascota) {
        Organizacion org= obtenerOrgMasCercana(rescatista.getDireccionEncuentroMascota());
        org.getListaPublicaciones().add(new PublicacionMascotaPerdida(rescatista,fotosMascota,estadoMascota));
    }

    public static void generarPublicacionMascotaEnAdopcion(Duenio duenio, String descripcionMascota, List<String> fotosMascota, Organizacion organizacion) {
        organizacion.getListaPublicaciones().add(new PublicacionMascotaPerdida(duenio,fotosMascota,descripcionMascota));
    }

    public void agregarOrganizacion(Organizacion organizacion){
        organizaciones.add(organizacion);
    }
}
