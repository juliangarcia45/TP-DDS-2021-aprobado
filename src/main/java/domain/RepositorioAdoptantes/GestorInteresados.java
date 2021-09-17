package domain.RepositorioAdoptantes;

import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.organizacion.Duenio;
import domain.organizacion.PublicacionAdoptante;
import domain.organizacion.Recomendacion;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public abstract class GestorInteresados {
    public static void crearPublicacionAdoptante(Duenio interesado, List<RespuestaAdopcion> preferencias){
        RepositorioAdoptantes.agregarInteresado(new PublicacionAdoptante(interesado,preferencias));
    }

    public static void eliminarPublicacionAdoptante(Duenio duenio){
        List<Duenio> duenios=RepositorioAdoptantes.getInteresados().stream().map(publicacion->publicacion.getInteresado()).collect(Collectors.toList());
        int index=duenios.indexOf(duenio);
        RepositorioAdoptantes.getInteresados().remove(RepositorioAdoptantes.getInteresados().get(index));
    }

    public void recomendacionSemanal(){
        RepositorioAdoptantes.getScheduler().scheduleAtFixedRate(Recomendacion.recomendarPublicacion(), 7, 7, TimeUnit.DAYS);
    }
}
