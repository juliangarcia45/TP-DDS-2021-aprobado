package domain.organizacion;


import domain.autenticacion.Usuario;

import java.util.List;

public class GeneradorPublicaciones {
    private static final GeneradorPublicaciones instance = new GeneradorPublicaciones();


    public static GeneradorPublicaciones getInstance() {
        return instance;
    }

    public void generarPublicacion(Usuario usuario, String estadoMascota, List<String> fotos, String descripcion){
        Publicacion publicacionACrear = new Publicacion(usuario,estadoMascota,fotos,descripcion);

        if(usuario.getOrganizacionAsociada().getVoluntarios().get(0).aprobarPublicacion(publicacionACrear)){
            usuario.getOrganizacionAsociada().agregarPublicacion(publicacionACrear);

        }
    }
}
