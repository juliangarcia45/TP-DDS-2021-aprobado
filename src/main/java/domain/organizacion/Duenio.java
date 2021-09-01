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
        return this.getOrganizacionAsociada().publicacionesDeMascotasPerdidas();
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

    public List<Publicacion> buscarMascotasEnAdopcion(){
        return this.getOrganizacionAsociada().publicacionesDeMascotasEnAdopcion();
    }
    
    public void darEnAdopcion(Mascota mascota){
        PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = GeneradorPublicaciones.generarPublicacionMascotaEnAdopcion(this,mascota.getDescripcion(),mascota.getFotos());
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


