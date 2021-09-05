package domain;

import domain.autenticacion.Usuario;
import domain.notificacion.Contacto;
import domain.notificacion.MedioDeNotificacion;
import domain.notificacion.Sms;
import domain.notificacion.WhatsApp;
import domain.organizacion.Duenio;
import domain.organizacion.Mascota;
import domain.organizacion.Organizacion;
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
            mascotaDuenio = new Mascota.MascotaBuilder(null,null).nombre("Mascota").build();
            organizacion= new Organizacion();

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
            organizaciones.add(organizacion);
        }
        @Test
        public void notificarUsuario()  {
            cliente.registrarMascota(mascotaDuenio);
            System.out.println(mascotaDuenio.getDuenio());
            cliente.darEnAdopcion(mascotaDuenio, organizaciones);

        }
    }

