package domain.entities.notificacion;
import domain.entities.organizacion.Duenio;
import org.apache.commons.mail.EmailException;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class notificarUsuarioTest {
    public static final String ACCOUNT_SID = "ACe762ffd4d3ff9ed0ab2390e757002aba";
    public static final String AUTH_TOKEN = "c388f5cc7432cbe70c26cd069565ef12";
    Duenio cliente;
    @Before
    public void init(){
        // Creo un cliente
        cliente = new Duenio("cliente","cliente123");
        // Creo medios de notificacion y les asigno su estrategia de notificacion
        MedioDeNotificacion whatsapp = new MedioDeNotificacion();
        whatsapp.setEstrategiaNotificacion(new WhatsApp());
        MedioDeNotificacion sms = new MedioDeNotificacion();
        sms.setEstrategiaNotificacion(new Sms());
        // Creo una lista para poder meter los mediosDeNotificacion en el Contacto del duenio y asi cargarselos.
        List<MedioDeNotificacion> mediosDeNotificacion =Arrays.asList(whatsapp,sms);
        Contacto contactoCliente = new Contacto("ContactoNombre","ContactoApellido",
                "contacto@gmail.com","1156956024",mediosDeNotificacion);

        cliente.setMediosDeContacto(Arrays.asList(contactoCliente));
    }
    @Test
    public void notificarUsuario() throws EmailException {
        // pada cada tipo de contacto que tenga el cliente le notifico
    List<Contacto> mediosDeContacto = cliente.getMediosDeContacto();
    for(Contacto contacto : mediosDeContacto){
        contacto.notificar("Probando Whatsapp");
        }
    }
}