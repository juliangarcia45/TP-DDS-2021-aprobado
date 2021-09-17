package domain.RepositorioAdoptantes;

import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.organizacion.Duenio;
import domain.organizacion.GestorPublicaciones;
import domain.organizacion.PublicacionAdoptante;
import domain.organizacion.Recomendacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RepositorioAdoptantes {
    private static final RepositorioAdoptantes instance = new RepositorioAdoptantes();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    private static List<PublicacionAdoptante> interesados=new ArrayList<>();

    public static RepositorioAdoptantes getInstance() {
        return instance;
    }

    public static void agregarInteresado(PublicacionAdoptante interesado){
        interesados.add(interesado);
    }
    public static void removerInteresado(PublicacionAdoptante interesado){
        interesados.remove(interesado);
    }
    public static List<PublicacionAdoptante> getInteresados() {
        return interesados;
    }
    public static ScheduledExecutorService getScheduler() {
        return scheduler;
    }





}
