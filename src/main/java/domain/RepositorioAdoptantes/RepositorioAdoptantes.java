package domain.RepositorioAdoptantes;

import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.entidadPersistente.EntidadPersistente;
import domain.organizacion.Duenio;
import domain.organizacion.GestorPublicaciones;
import domain.organizacion.PublicacionAdoptante;
import domain.organizacion.Recomendacion;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Entity
@Table(name="adoptantes")
public class RepositorioAdoptantes extends EntidadPersistente {
    @Transient
    private static final RepositorioAdoptantes instance = new RepositorioAdoptantes();
    @Transient
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @OneToMany
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
