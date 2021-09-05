package domain.organizacion;

import domain.RepositorioAdoptantes.RepositorioAdoptantes;

import java.util.ArrayList;
import java.util.List;
public class Recomendacion {
    private static final Recomendacion instance = new Recomendacion();

    public static Recomendacion getInstance() {
        return instance;
    }

    public static Publicacion obtenerRecomendacion(PublicacionAdoptante publicacionInteresado){
        List<Publicacion> publicaciones=GestorPublicaciones.obtenerPublicacionesMascEnAdopcion();
        int index = 0;
        for (Publicacion publicacion : publicaciones) {
            while (publicaciones.iterator().hasNext()) {
                if(publicacion instanceof PublicacionMascotaEnAdopcion){
                    if(((PublicacionMascotaEnAdopcion) publicacion).getRespuestas().equals(publicacionInteresado.getRespuestas())){
                        index=publicaciones.indexOf(publicacion);
                    }
                }
            }
        }
        return publicaciones.get(index);
    }

    public static void recomendarPublicacion(){
        RepositorioAdoptantes.getInteresados().stream().forEach(interesado->obtenerRecomendacion(interesado));
    }
}
