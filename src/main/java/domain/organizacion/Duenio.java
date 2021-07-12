package domain.organizacion;

import domain.autenticacion.Usuario;

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

    public void generarPublicacionAdopcion(Mascota mascotaEnAdopcion){

    }
    public void darEnAdopcion(Mascota mascota){
        PublicacionMascotaEnAdopcion publicacionMascotaEnAdopcion = GeneradorPublicaciones.generarPublicacionMascotaEnAdopcion(this,mascota.getDescripcion(),mascota.getFotos());
        }
    }

