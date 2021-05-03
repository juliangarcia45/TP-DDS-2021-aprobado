package domain.autenticacion.validadorDeContrasenias;

import domain.autenticacion.Usuario;

public class RegistroDeUsuarios {
    private static final RegistroDeUsuarios instance = new RegistroDeUsuarios();

    private RegistroDeUsuarios() {
    }

    public static RegistroDeUsuarios getInstance() {
        return instance;
    }

    public void registrar(String usuario, String contrasenia){
        Usuario u = new Usuario(usuario,contrasenia);
    }
}
