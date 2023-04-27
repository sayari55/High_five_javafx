/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.util.Properties;
import javax.mail.Session;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author khaliljebali
 */
public class JavaMailUtil {
    public static void sendMail(String recipient , String subject , String mailText) throws Exception{
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        
        properties.put("mail.smtp.auth" , "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host" , "smtp.gmail.com");
        properties.put("mail.smtp.port" , "587");
        
        String myAccountEmail = "khalil.jebali@esprit.tn";
        String password = "223JMT5259";
        
        Session session = Session.getInstance(properties , new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail , password);
                
            }
            
        });
        
        Message message = prepareMessage(session , myAccountEmail , recipient , subject , mailText); 
        Transport.send(message);
        System.out.println("mail sent successfully");
        
    }

    private static Message prepareMessage(Session session , String myAccountEmail , String recipient ,String subject , String mailText) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO , new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(mailText);
            return message;
                        
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    
}
