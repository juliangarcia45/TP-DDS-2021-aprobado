package domain.entities.organizacion;

import domain.entities.autenticacion.Usuario;

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
