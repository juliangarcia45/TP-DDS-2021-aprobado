package domain.persistencia;

import domain.BDUtils.EntityManagerHelper;
import domain.notificacion.Contacto;
import domain.notificacion.MedioDeNotificacion;
import domain.organizacion.Documento;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;
import domain.organizacion.Organizacion;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class persistiendoOrganizacionYUsuario {
    @Test
    public void persistir(){
        //Duenio
        Duenio raul= new Duenio("raul","123456");
        List<Contacto> contactosRaul=new ArrayList<>();
        MedioDeNotificacion medioRaul=new MedioDeNotificacion();
        List<MedioDeNotificacion> listaMedios=new ArrayList<>();
        listaMedios.add(medioRaul);
        Contacto contactoRaul=new Contacto("Raul","Gonzalez","raul@gmail.com",49751235,listaMedios);
        medioRaul.setContactoANotificar(contactoRaul);
        contactosRaul.add(contactoRaul);
        Documento documentoRaul= new Documento(123456,"DNI");
        Mascota calli=new Mascota.MascotaBuilder(null,"a").apodo("calli").edad(14).build();
        raul.setNombre("Raul");
        raul.setApellido("Gonzalez");
        raul.setDocumento(documentoRaul);
        raul.registrarMascota(calli);
        raul.setMediosDeContacto(contactosRaul);

        EntityManagerHelper.beginTransaction();
        EntityManagerHelper.getEntityManager().persist(raul);
        EntityManagerHelper.commit();

    }
}
