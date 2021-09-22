package domain.organizacion;

import domain.entidadPersistente.EntidadPersistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="organizaciones")
public class RepositorioOrganizaciones extends EntidadPersistente {
    @Transient
    private static final RepositorioOrganizaciones instance = new RepositorioOrganizaciones();

    @OneToMany
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
