package domain.organizacion;

import domain.autenticacion.Usuario;

public class Voluntario extends Usuario {
    public Voluntario(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public Boolean aprobarPublicacion(Publicacion aviso){

        return true;
    }
}
