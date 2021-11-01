package domain.entities.notificacion;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Entity
@DiscriminatorValue("WPP")
public class WhatsApp extends FormaDeNotificacion {
    @Override
    public void notificar(Integer telefono,String mensaje) {
        System.out.println("Notificar al usuario por whatsapp");
        String ACCOUNT_SID = "ACe762ffd4d3ff9ed0ab2390e757002aba";
         String AUTH_TOKEN = "c388f5cc7432cbe70c26cd069565ef12";
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
                    new com.twilio.type.PhoneNumber("whatsapp:+549"+telefono),
                    new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                    mensaje)
                    .create();
    }
}