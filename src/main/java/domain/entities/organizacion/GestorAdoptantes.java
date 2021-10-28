package domain.entities.organizacion;


import domain.Repositorios.Daos.DAO;
import domain.Repositorios.Daos.DAOHibernate;
import domain.Repositorios.RepositorioDeAdoptantes;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GestorAdoptantes {
    private static final GestorAdoptantes instance = new GestorAdoptantes();
    public static GestorAdoptantes getInstance() {
        return instance;
    }

    private static DAO<PublicacionAdoptante> dao= new DAOHibernate<>(PublicacionAdoptante.class);
    private static RepositorioDeAdoptantes repo=new RepositorioDeAdoptantes(dao);

    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public static void agregarInteresado(PublicacionAdoptante interesado){
        repo.agregar(interesado);
    }

    public static void removerInteresado(PublicacionAdoptante interesado){
        repo.eliminar(interesado);
    }

    public static void removerPorId(int idInteresado){
        repo.eliminar(repo.buscarInteresado(idInteresado));
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
        repo.buscarTodosLosAdoptantes().forEach(interesado->obtenerRecomendacion(interesado));
        return null;
    }

    public void recomendacionSemanal(){
        scheduler.scheduleAtFixedRate(recomendarPublicacion(), 7, 7, TimeUnit.DAYS);
    }




}
