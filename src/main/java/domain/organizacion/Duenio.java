package domain.organizacion;

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
        return GeneradorPublicaciones.obtenerPublicacionesMascPerdidas();
    }

    public void contactarAlQueLaRescato(Publicacion publicacion){
        this.contactar(publicacion.getUsuario());
    }

    public void contactar(Usuario usuario){
        List <Contacto> listaContactosDuenio = usuario.getMediosDeContacto();
        for(Contacto contacto : listaContactosDuenio){
            contacto.notificar(this.getNombre() + " " + this.getApellido() + " se reportó como el dueño de la mascota que encontraste, sus contactos son: " + this.getMediosDeContacto() );
        }
    }
    //Mascota adoptada
//VER ACA
   // public List<Publicacion> buscarMascotasEnAdopcion(){
     //   return GeneradorPublicaciones.obtenerPublicacionesMascEnAdopcion();
    //}
    
    public void darEnAdopcion(Mascota mascota, Organizacion organizacionFav){
        GeneradorPublicaciones.generarPublicacionMascotaEnAdopcion(this,mascota.getDescripcion(),mascota.getFotos(), organizacionFav);
        this.getMascotas().remove(mascota);
        mascota.setDuenio(null);
    }

    public void adoptarMascota(Mascota mascota) {
        List <Contacto> mediosDeContacto = mascota.getDuenio().getMediosDeContacto();
        for(Contacto contacto : mediosDeContacto){
            contacto.notificar("Quieren adoptar a tu mascota");
        }
    }

    // public PublicacionAdoptante quieroAdoptarUnaMascota(String descripcion , List<String> fotos){
    //     return GeneradorPublicaciones.generarPublicacionAdoptante(this,descripcion,fotos);
    // }


    }


