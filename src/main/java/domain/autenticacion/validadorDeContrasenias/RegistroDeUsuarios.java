package domain.autenticacion.validadorDeContrasenias;

import domain.autenticacion.Usuario;
import domain.notificacion.Contacto;
import domain.organizacion.Documento;
import domain.organizacion.Duenio;

import java.util.Date;
import java.util.List;

public class RegistroDeUsuarios {
    private static final RegistroDeUsuarios instance = new RegistroDeUsuarios();

    private RegistroDeUsuarios() {
    }

    public static RegistroDeUsuarios getInstance() {
        return instance;
    }

    public void registrar(String usuario, String contrasenia, String nombre, String apellido, List<Contacto> mediosDeContacto, Documento documento, Date fechaNacimiento){
        Usuario u = new Usuario(usuario, contrasenia, nombre, apellido, mediosDeContacto, documento, fechaNacimiento) {
        };
    }
}
