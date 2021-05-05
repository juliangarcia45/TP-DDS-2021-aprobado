package domain.notificacion;

import com.twilio.Twilio;
import com.twilio.converter.Promoter;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import java.net.URI;
import java.math.BigDecimal;

public class Example {
    // Find your Account Sid and Token at twilio.com/console
    public static final String ACCOUNT_SID = "ACe762ffd4d3ff9ed0ab2390e757002aba";
    public static final String AUTH_TOKEN = "c388f5cc7432cbe70c26cd069565ef12";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+5491156956024"),
            new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
            "Que onda samu")
            .create();

        System.out.println(message.getBody());
}
}