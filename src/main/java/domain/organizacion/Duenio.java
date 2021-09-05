package domain.organizacion;

import domain.PreguntasAdopcion.RespuestaAdopcion;
import domain.RepositorioAdoptantes.RepositorioAdoptantes;
import domain.autenticacion.Usuario;
import domain.notificacion.Contacto;

import javax.sound.midi.SysexMessage;
import java.util.ArrayList;
import java.util.List;

public class Duenio extends Usuario {
    private List<Mascota> mascotas = new ArrayList<Mascota>();

    public Duenio(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }
    
    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void registrarMascota(Mascota mascota){
        this.mascotas.add(mascota);
        mascota.setDuenio(this);
    }

    //Mascota perdida
    public List<Publicacion> buscarMascotaPerdida(){
        return GestorPublicaciones.obtenerPublicacionesMascPerdidas();
    }

    //Mascota adoptada
//VER ACA
    public List<Publicacion> buscarMascotasEnAdopcion(){
       return GestorPublicaciones.obtenerPublicacionesMascEnAdopcion();
    }
    
    public void darEnAdopcion(Mascota mascota, List<Organizacion> organizaciones){
        organizaciones.stream().forEach(organizacion->GestorPublicaciones.generarPublicacionMascotaEnAdopcion(this,mascota, organizacion));
        this.getMascotas().remove(mascota);
        mascota.setDuenio(null);
    }

    //Duenio interesado en adoptar
    public void suscribirseARecomendacionesDeAdopcion(List<RespuestaAdopcion> preferencias){
        RepositorioAdoptantes.crearPublicacionAdoptante(this,preferencias);
    }
    public void desuscribirseARecomendacionesDeAdopcion(){
        RepositorioAdoptantes.eliminarPublicacionAdoptante(this);
    }




    // public PublicacionAdoptante quieroAdoptarUnaMascota(String descripcion , List<String> fotos){
    //     return GeneradorPublicaciones.generarPublicacionAdoptante(this,descripcion,fotos);
    // }


    }


