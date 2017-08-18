/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package melektro;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import static melektro.PublicIPAddress.Log;

/**
 *
 * @author Marius
 */
public class Email {
    public static boolean EMailSender(String host, String port, final String from, final String pass, String to, String cc, String sub, String mess) {
        Properties props = System.getProperties();
        props.setProperty("mail.smtp.auth", "true");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.host", host);
        props.setProperty("mail.smtp.port", port);
        Authenticator auth;
        auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, pass);
            }
        };
        try {
            Session session = Session.getInstance(props, auth);
            //session.setDebug(verbose);
            Transport tr;
            tr = session.getTransport("smtp");
            if (!(tr instanceof SMTPTransport)) {
                Log("This is NOT an SMTPTransport:[" + tr.getClass().getName() + "]");
            }
            Message message;
            message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            message.setSubject(sub);
            message.setText(mess);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            Log("Exception in EMailSender:" + e.getMessage());
            return false;
        }
    }
    
}
