package domain;

import domain.entities.autenticacion.Usuario;
import domain.entities.notificacion.Contacto;
import domain.entities.notificacion.MedioDeNotificacion;
import domain.entities.notificacion.Sms;
import domain.entities.notificacion.WhatsApp;
import domain.entities.organizacion.Duenio;
import domain.entities.organizacion.Mascota;
import domain.entities.organizacion.Organizacion;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class entrega3Test {

        Duenio cliente;
        Usuario usuario;
    Mascota mascotaDuenio;
    Organizacion organizacion;
    List<Organizacion> organizaciones=new ArrayList<>();
        @Before
        public void init(){
            // Creo un cliente
            cliente = new Duenio("cliente","cliente123");
            mascotaDuenio = new Mascota();
            mascotaDuenio.setNombre("Mascota");
            organizacion= new Organizacion();

            // Creo medios de notificacion y les asigno su estrategia de notificacion
            MedioDeNotificacion whatsapp = new MedioDeNotificacion();
            whatsapp.setEstrategiaNotificacion(new WhatsApp());
            MedioDeNotificacion sms = new MedioDeNotificacion();
            sms.setEstrategiaNotificacion(new Sms());
            // Creo una lista para poder meter los mediosDeNotificacion en el Contacto del duenio y asi cargarselos.
            List<MedioDeNotificacion> mediosDeNotificacion = Arrays.asList(whatsapp,sms);
            Contacto contactoCliente = new Contacto("ContactoNombre","ContactoApellido",
                    "contacto@gmail.com","1156956024",mediosDeNotificacion);

            cliente.setMediosDeContacto(Arrays.asList(contactoCliente));
            organizaciones.add(organizacion);
        }
        @Test
        public void notificarUsuario()  {
            cliente.registrarMascota(mascotaDuenio);
            System.out.println(mascotaDuenio.getDuenio());
            cliente.darEnAdopcion(mascotaDuenio, organizaciones);

        }
    }

