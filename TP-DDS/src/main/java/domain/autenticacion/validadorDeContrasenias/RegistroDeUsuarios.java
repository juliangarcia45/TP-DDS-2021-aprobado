package domain.autenticacion.validadorDeContrasenias;

public class RegistroDeUsuarios {
    private static final RegistroDeUsuarios instance = new RegistroDeUsuarios();

    private RegistroDeUsuarios() {
    }

    public static RegistroDeUsuarios getInstance() {
        return instance;
    }

    public void registrar(String usuario, String contrasenia){
        String newHashedPassword = BCrypt.hashpw(contrasenia,newSalt);
        Usuario u = new Usuario(nombre, newSalt, newHashedPassword, null);
        UsuarioMapper.getInstance().insert(u);
    }
}
