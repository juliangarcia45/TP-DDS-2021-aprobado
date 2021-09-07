package domain.organizacion;

import domain.RepositorioAdoptantes.RepositorioAdoptantes;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Recomendacion {
    private static final Recomendacion instance = new Recomendacion();



    public static Recomendacion getInstance() {
        return instance;
    }

    public static Publicacion obtenerRecomendacion(PublicacionAdoptante publicacionInteresado){
        List<Publicacion> publicaciones=GestorPublicaciones.obtenerPublicacionesMascEnAdopcion();
        int index = 0;
        for (Publicacion publicacion : publicaciones) {
                if(publicacion instanceof PublicacionMascotaEnAdopcion){
                    if(((PublicacionMascotaEnAdopcion) publicacion).getRespuestas().equals(publicacionInteresado.getRespuestas())){
                        index=publicaciones.indexOf(publicacion);
                    }
                }
        }
        return publicaciones.get(index);
    }

    public static Runnable recomendarPublicacion(){
        RepositorioAdoptantes.getInteresados().stream().forEach(interesado->obtenerRecomendacion(interesado));
        return null;
    }


}
