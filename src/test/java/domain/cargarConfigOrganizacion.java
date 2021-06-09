package domain;

import domain.organizacion.Organizacion;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;

public class cargarConfigOrganizacion {
    Organizacion organizacion;
    @Before
    public void initOrganizacion() throws IOException {
        organizacion = new Organizacion();
        organizacion.definirTamanioYFormatoEstandar();
        System.out.println(organizacion.getAnchoFoto());
        System.out.println(organizacion.getLargoFoto());
        System.out.println(organizacion.getFormatoEstandar());
    }
    @Test
    public void normalizarFoto2() throws IOException {
            organizacion.normalizarFoto2("assets/perro.png","assets/");
    }

}
