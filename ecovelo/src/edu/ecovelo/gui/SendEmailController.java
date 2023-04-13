/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.gui;


import java.net.URL;
import java.time.LocalDate;
import java.util.Properties;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * FXML Controller class
 *
 * @author benza
 */
public class SendEmailController implements Initializable {

    @FXML
    private TextField toField;
    @FXML
    private TextField subjectField;
    @FXML
    private Button sendButton;
    @FXML
    private Label statusLabel;
    @FXML
    private TextField messageField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }


    public static void sendEmail(String emailenv, String sub,String mess) {
        // Récupérer l'adresse email et le mot de passe pour le compte que vous utiliserez pour envoyer l'email
        String username = "youssefkhemakhem2001@gmail.com";
        String password = "xdpnvovsrtmclqnx";

        // Définir l'adresse et le port du serveur SMTP pour votre fournisseur de services email
        String smtpHost = "smtp.gmail.com";
        int smtpPort = 587;

        LocalDate currentDate = LocalDate.now();

// Affichage de la date dans le format JJ/MM/YYYY
        String formattedDate = currentDate.getDayOfMonth() + "/" + currentDate.getMonthValue() + "/" + currentDate.getYear();
        // Définir le destinataire, l'objet et le message pour l'email
        String to = emailenv;
        String subject = sub;
        String message = mess;
                

        // Créer un objet Properties pour contenir les paramètres du serveur SMTP
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtpHost);
        props.put("mail.smtp.port", smtpPort);

        // Créer un objet Session pour authentifier le compte email
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Créer un objet MimeMessage pour représenter le message email
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress(username));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            // Envoyer le message email
            Transport.send(mimeMessage);

            // Mettre à jour le label de statut pour indiquer que l'email a été envoyé avec succès
        } catch (MessagingException e) {
            // Mettre à jour le label de statut pour indiquer qu'il y a eu une erreur lors de l'envoi de l'email

            e.printStackTrace();
        }
    }

}
