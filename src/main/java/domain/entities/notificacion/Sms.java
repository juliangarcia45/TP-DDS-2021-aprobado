package domain.entities.notificacion;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

@Entity
@DiscriminatorValue("SMS")
public class Sms extends FormaDeNotificacion {
    @Override
    public void notificar(Integer telefono, String mensaje) {
        String ACCOUNT_SID = "ACe762ffd4d3ff9ed0ab2390e757002aba";
        String AUTH_TOKEN = "c388f5cc7432cbe70c26cd069565ef12";
        System.out.println("Notificar al usuario por sms");
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+54"+telefono),"MG4aea5e6a5b6b1686074a29a32e7ca361",
                mensaje)
                .create();
    }
}
