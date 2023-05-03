/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Reservation;
import entities.Station;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import services.ReservationService;
import services.StationService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ResFrontController implements Initializable {

    @FXML
    private ComboBox<Station> station_loc1;
    @FXML
    private DatePicker date_fin;
    @FXML
    private DatePicker date_debut;
    @FXML
    private Button pb_ajout;
    @FXML
    private TableView<Reservation> affichage_res;
    @FXML
    private TableColumn<?, ?> debutCol;
    @FXML
    private TableColumn<?, ?> finCol;
    @FXML
    
    private TableColumn<?, ?> stationCol;
    @FXML
    private Button pb_reserver;
    @FXML
    private DatePicker mod_date_deb;
    @FXML
    private DatePicker mod_date_fin;
    
    @FXML
    private Button pb_modif;
    @FXML
    private ComboBox<Station> station_loc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
 
    
    // Create an instance of the VeloService class
    ReservationService ReservationService = new ReservationService();
    List<Reservation> res = ReservationService.afficher();
    affichage_res.setItems(FXCollections.observableList(res));
    
// Set the cell value factories for the columns
debutCol.setCellValueFactory(new PropertyValueFactory<>("date_debut"));
finCol.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
stationCol.setCellValueFactory(new PropertyValueFactory<>("station_nom"));
StationService stationService = new StationService();

List<Station> stations = null;
try {
    stations = stationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(ResFrontController.class.getName()).log(Level.SEVERE, null, ex);
}


station_loc1.getItems().setAll(stations);
station_loc.getItems().setAll(stations);


} catch (SQLException ex) {
    Logger.getLogger(ResFrontController.class.getName()).log(Level.SEVERE, null, ex);
}


        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws AddressException, MessagingException {
        
           
    Reservation res = new Reservation();
    Station s=new Station();
    LocalDate debut = date_debut.getValue();
    LocalDate fin = date_fin.getValue();
   


    // Check if debut is before fin
    if (debut == null || fin == null || debut.isAfter(fin)) {
        // Show alert if debut is after fin or if dates are not selected
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Erreur de saisie");
        alert.setHeaderText(null);
        alert.setContentText("La date de début doit être avant la date de fin !");
        alert.showAndWait();
        return; // Stop the method execution
    }

    res.setDatedebut(Date.valueOf(debut));
    res.setDatefin(Date.valueOf(fin));
    res.setEtat(0);
    res.setStation_id(station_loc.getValue().getId());
    ReservationService ReservationService = new ReservationService();
    try {
        ReservationService.ajouter(res);
    } catch (SQLException ex) {
        Logger.getLogger(ResFrontController.class.getName()).log(Level.SEVERE, null, ex);
    }
    List<Reservation> reservations = null;
    try {
        reservations = ReservationService.afficher();
    } catch (SQLException ex) {
        Logger.getLogger(ResFrontController.class.getName()).log(Level.SEVERE, null, ex);
    }
    affichage_res.setItems(FXCollections.observableList(reservations));
    //mail
    String host = "smtp.gmail.com";
            String utilisateur = "khalil.belhedi@esprit.tn";
            String pass = "201JMT4481";
            String SendTo = "khalil.belhedi@esprit.tn";
            String from = "khalil.belhedi@esprit.tn";
            String Subject = "Information à propos l'ajout d'une reservation ";
            String textMessage = "<html><body style='font-family: Arial, sans-serif; color: #333;'><h1 style='color: #0066CC;'>Une nouvelle reservation a été ajoutée de "+res.getDate_debut()+" à "+res.getDate_fin()+"</h1><p style='color: #666;'>Cordialement,<br>Equipe EcoVelo</p></body></html>";
            boolean seesionDebug = false;
            Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");
          //  Security.addProvider(new Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(seesionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(SendTo)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(Subject);
            msg.setContent(textMessage, "text/html");


            Transport transport = mailSession.getTransport("smtp");
            transport.connect(host, utilisateur, pass);
            transport.sendMessage(msg, msg.getAllRecipients());
            transport.close();
            System.out.println("message send successfully");
    
    }

    @FXML
    private void selection(MouseEvent event) {
    }

    @FXML
    private void reserver(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("ResFront.fxml"));
            Scene scene = new Scene(root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    
    }

    @FXML
    private void modifier(ActionEvent event) throws ParseException {
         Reservation selectedReservation = affichage_res.getSelectionModel().getSelectedItem();
// Check if a reservation is selected
if (selectedReservation == null) {
    // Show alert if no reservation is selected
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une réservation à modifier !");
    alert.showAndWait();
    return; // Stop the method execution
}

LocalDate debut = mod_date_deb.getValue();
LocalDate fin = mod_date_fin.getValue();

// Check if debut is before fin
if (debut == null || fin == null || debut.isAfter(fin)) {
    // Show alert if debut is after fin or if dates are not selected
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("La date de début doit être avant la date de fin !");
    alert.showAndWait();
    return; // Stop the method execution
}

// Update the selected reservation with the modified values
selectedReservation.setDatedebut(Date.valueOf(debut));
selectedReservation.setDatefin(Date.valueOf(fin));
selectedReservation.setEtat(0);
selectedReservation.setStation_id(station_loc1.getValue().getId());

ReservationService reservationService = new ReservationService();
try {
    reservationService.modifier(selectedReservation);
} catch (SQLException ex) {
    Logger.getLogger(ResFrontController.class.getName()).log(Level.SEVERE, null, ex);
}

// Refresh the list of reservations displayed in the table view
List<Reservation> reservations = null;
try {
    reservations = reservationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(ResFrontController.class.getName()).log(Level.SEVERE, null, ex);
}
affichage_res.setItems(FXCollections.observableList(reservations));

    }
    
}
