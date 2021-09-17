package domain.organizacion;

import java.util.ArrayList;
import java.util.List;

public class RepositorioOrganizaciones {
    private static final RepositorioOrganizaciones instance = new RepositorioOrganizaciones();

    private static List<Organizacion> organizaciones= new ArrayList<>();

    public static RepositorioOrganizaciones getInstance() {
        return instance;
    }


    public static void agregarOrganizacion(Organizacion organizacion) {
        organizaciones.add(organizacion);
    }

    public static void eliminarOrganizacion(Organizacion organizacion){
        organizaciones.remove(organizacion);
    }

    public static List<Organizacion> getOrganizaciones() {
        return organizaciones;
    }
}
