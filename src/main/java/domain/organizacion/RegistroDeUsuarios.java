package domain.organizacion;

import domain.autenticacion.Administrador;
import domain.autenticacion.Usuario;
import domain.notificacion.Contacto;

import java.util.Date;
import java.util.List;

public class RegistroDeUsuarios {
    private static final RegistroDeUsuarios instance = new RegistroDeUsuarios();

    public static RegistroDeUsuarios getInstance() {
        return instance;
    }

    public static Usuario registrar(Usuario usuario){
         usuario = new Usuario("default","default") {
        };
        return usuario;
    }
}
