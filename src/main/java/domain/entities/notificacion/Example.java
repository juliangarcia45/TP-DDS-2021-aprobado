package domain.entities.notificacion;


import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Example {
    // Find your Account Sid and Token at twilio.com/user/account
    public static final String ACCOUNT_SID = "ACafd3491524efec0ea4aff04426dcc707";
    public static final String AUTH_TOKEN = "97f8fa9e19d248e257e5d9f877b65ac7";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(new PhoneNumber("+5491168112416"),
                new PhoneNumber("+12055128105"),
                "FAQ").create();

        System.out.println(message.getSid());
    }
}