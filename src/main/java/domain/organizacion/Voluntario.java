package domain.organizacion;

import domain.autenticacion.Usuario;

public class Voluntario extends Usuario {
    private Organizacion organizacion;
    public Voluntario(String usuario, String contrasenia) {
        super(usuario, contrasenia);
    }

    public Publicacion obtenerPublicacion(){
       return organizacion.obtenerPublicacionesSegun(EstadoPublicacion.EN_ESPERA).get(0);
    }

    public void aprobar(Publicacion solicitudPublicacion){
        solicitudPublicacion.aprobarPublicacion();
    }

    public void rechazar(Publicacion solicitudPublicacion){
        organizacion.obtenerPublicacionesSegun(EstadoPublicacion.EN_ESPERA).remove(solicitudPublicacion);
    }
}
