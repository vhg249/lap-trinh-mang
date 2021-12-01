/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mail;

import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author a
 */
public class Mail {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MessagingException {
        final String username = "vhuongptit@gmail.com";
        final String password = "";
        
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", true);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", 587);
        prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        
        Session session = Session.getDefaultInstance(prop,
            new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
            }
        });
        
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("vhuongptit@gmail.com"));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("laptrinhmangptit@gmail.com"));
        msg.setSubject("4 - B18DCAT119 - Nguyen Viet Huong");
        msg.setText("");
        msg.setSentDate(new Date());
        
        Transport.send(msg);
        System.out.println("ok");
        
    }
    
}
