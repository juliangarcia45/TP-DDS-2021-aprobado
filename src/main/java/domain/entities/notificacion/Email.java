package domain.entities.notificacion;

import config.Config;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MAIL")
public class Email extends FormaDeNotificacion{

    public void notificar(Contactable contactable) throws EmailException {
        org.apache.commons.mail.Email email = new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(Config.MAIL_GMAIL_CREDENTIAL, Config.PASSWORD_GMAIL_CREDENTIAL));
        email.setSSLOnConnect(true);
        email.setFrom(Config.MAIL_GMAIL_CREDENTIAL);
        email.setSubject("Patitas");
        email.setMsg(contactable.getMensaje());
        email.addTo(contactable.getEmail());
        email.send();

    }
}
