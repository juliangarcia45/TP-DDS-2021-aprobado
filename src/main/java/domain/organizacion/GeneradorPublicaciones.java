package domain.organizacion;


import domain.autenticacion.Usuario;

import java.util.List;

public class GeneradorPublicaciones {

    private static final GeneradorPublicaciones instance = new GeneradorPublicaciones();

    public static GeneradorPublicaciones getInstance() {
        return instance;
    }

    public static PublicacionMascotaPerdida generarPublicacionMascotaPerdida(Usuario rescatista, String estadoMascota, List<String> fotosMascota) {
        return new PublicacionMascotaPerdida(rescatista,fotosMascota,estadoMascota);
    }
    public static PublicacionMascotaEnAdopcion generarPublicacionMascotaEnAdopcion(Duenio duenio, String descripcionMascota, List<String> fotosMascota) {
        return new PublicacionMascotaEnAdopcion(duenio,descripcionMascota,fotosMascota);
    }

    public static PublicacionAdoptante generarPublicacionAdoptante(Usuario usuarioAdoptante,String descripcion, List<String> fotosAdoptante ) {
        return new PublicacionAdoptante(usuarioAdoptante,descripcion,fotosAdoptante);
    }
}
