package domain.RepositorioAdoptantes;

import domain.organizacion.PublicacionAdoptante;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class RepositorioAdoptantes {
    private static final RepositorioAdoptantes instance = new RepositorioAdoptantes();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static List<PublicacionAdoptante> interesados=new ArrayList<PublicacionAdoptante>();



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
