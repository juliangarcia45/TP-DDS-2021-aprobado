package domain.organizacion;

import domain.autenticacion.Usuario;

public class Voluntario extends Usuario {
    private Organizacion organizacion;
    public Voluntario(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public Publicacion obtenerPublicacion(){
       return organizacion.obtenerPublicacionesEnEspera().get(0);
    }

    public void aprobarPublicacion(Publicacion solicitudPublicacion){
        solicitudPublicacion.aprobarPublicacion();
    }

}
