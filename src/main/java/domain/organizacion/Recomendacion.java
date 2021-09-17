package domain.organizacion;

import domain.RepositorioAdoptantes.RepositorioAdoptantes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Recomendacion {
    private static final Recomendacion instance = new Recomendacion();



    public static Recomendacion getInstance() {
        return instance;
    }

    public static List<Publicacion> obtenerRecomendacion(PublicacionAdoptante publicacionInteresado){
        List<Publicacion> publicaciones=GestorPublicaciones.obtenerPublicacionesMascEnAdopcion();
        List<Publicacion> recomendaciones=new ArrayList<>();
        for (Publicacion publicacion : publicaciones) {
                if(publicacion instanceof PublicacionMascotaEnAdopcion){
                    if(((PublicacionMascotaEnAdopcion) publicacion).getRespuestas().equals(publicacionInteresado.getRespuestas())){
                        recomendaciones.add(publicacion);
                    }
                }
        }
        return recomendaciones;
    }

    public static Runnable recomendarPublicacion(){
        RepositorioAdoptantes.getInteresados().stream().forEach(interesado->obtenerRecomendacion(interesado));
        return null;
    }


}
