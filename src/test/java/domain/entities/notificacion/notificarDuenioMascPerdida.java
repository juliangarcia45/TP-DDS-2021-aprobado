package domain.entities.notificacion;

import domain.entities.organizacion.*;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class notificarDuenioMascPerdida {
    public static final String ACCOUNT_SID = "ACe762ffd4d3ff9ed0ab2390e757002aba";
    public static final String AUTH_TOKEN = "c388f5cc7432cbe70c26cd069565ef12";


    Organizacion patitas = new Organizacion();
    Duenio cliente;
    Mascota korone;
    List<Publicacion> publicaciones;
    Rescatista clark = new Rescatista("clark", "xD");
    @Before
    public void init() {
        // Creo un cliente
        cliente = new Duenio("cliente","cliente123");
        // Creo medios de notificacion y les asigno su estrategia de notificacion
        MedioDeNotificacion whatsapp = new MedioDeNotificacion();
        whatsapp.setEstrategiaNotificacion(new WhatsApp());
        MedioDeNotificacion sms = new MedioDeNotificacion();
        sms.setEstrategiaNotificacion(new Sms());
        // Creo una lista para poder meter los mediosDeNotificacion en el Contacto del duenio y asi cargarselos.
        List<MedioDeNotificacion> mediosDeNotificacion = Arrays.asList(whatsapp,sms);
        Contacto contactoCliente = new Contacto("ContactoNombre","ContactoApellido",
                "contacto@gmail.com",1156956024,mediosDeNotificacion);

        cliente.setMediosDeContacto(Arrays.asList(contactoCliente));
        korone= new Mascota.MascotaBuilder(null,null).nombre("doggo").build();
        cliente.registrarMascota(korone);

    }
    @Test
    public void notificarUsuario()  {
        clark.notificarDuenio(korone);
    }


}
