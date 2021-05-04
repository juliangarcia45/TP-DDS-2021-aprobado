package domain.organizacion;

import domain.autenticacion.Usuario;
import domain.notificacion.Contacto;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Duenio extends Usuario {
    private List<Mascota> mascotas = new ArrayList<Mascota>();

    public Duenio(String usuario, String contrasenia, String nombre, String apellido, List<Contacto> mediosDeContacto, Documento documento, Date fechaNacimiento) {
        super(usuario, contrasenia, nombre, apellido, mediosDeContacto, documento, fechaNacimiento);
    }


    public List<Mascota> getMascotas() {
        return mascotas;
    }

    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }

    public void registrarMascota(Mascota mascota){
        this.mascotas.add(mascota);
    }
}
