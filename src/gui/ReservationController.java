/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import entities.Reservation;
import entities.Station;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.List;
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
import services.ReservationService;
import services.StationService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class ReservationController implements Initializable {

    @FXML
    private Button pb_res;
    @FXML
    private Button pb_stat;
    @FXML
    private DatePicker mod_date_deb;
    @FXML
    private DatePicker mod_date_fin;
    @FXML
    private ComboBox<Station> station_loc1;
    @FXML
    private Button pb_valid;
    @FXML
    private Button pb_supp;
    @FXML
    private Button pb_modif;
    @FXML
    private TableView<Reservation> affichage_res;
    @FXML
    private TableColumn<?, ?> debutCol;
    @FXML
    private TableColumn<?, ?> finCol;
    @FXML
    private TableColumn<?, ?> etatCol;
    @FXML
    private TableColumn<?, ?> stationCol;

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
etatCol.setCellValueFactory(new PropertyValueFactory<>("etat"));
stationCol.setCellValueFactory(new PropertyValueFactory<>("station_nom"));
StationService stationService = new StationService();

List<Station> stations = null;
try {
    stations = stationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}


station_loc1.getItems().setAll(stations);
//station_loc1.getItems().setAll(stations);


} catch (SQLException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}

    }    

    @FXML
    private void res_admin(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
            Scene scene = new Scene(root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    }

    @FXML
    private void stat_admin(ActionEvent event) {
          try {
            Parent root = FXMLLoader.load(getClass().getResource("Station.fxml"));
            Scene scene = new Scene(root);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        }catch (IOException ex){
            System.out.println("Erreur"+ex.getMessage());
        }
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException, ParseException {
         Reservation selectedReservation = affichage_res.getSelectionModel().getSelectedItem();
        ReservationService rss =new ReservationService();
            rss.valider(selectedReservation);

               final String ACCOUNT_SID ="AC6322202e674e9c2ede42f41107988ea1";
               final String AUTH_TOKEN = "15e6276ab8399af2689300b26419d795";
            
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        com.twilio.rest.api.v2010.account.Message message = com.twilio.rest.api.v2010.account.Message.creator(
                new PhoneNumber("+21658404108"),
                new PhoneNumber("+16076954758"),
                "Votre reservation a été confirmé avec succées de " +selectedReservation.getDate_debut()+" à "+selectedReservation.getDate_fin())
            .create();
            
        //SendSms sms = new SendSms();
        //sms.sendSms();
        
        List<Reservation> reservations = null;
try {
    reservations = rss.afficher();
} catch (SQLException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}
affichage_res.setItems(FXCollections.observableList(reservations));

        
    }

    @FXML
    private void supprimer(ActionEvent event) {
           Reservation selectedReservation = affichage_res.getSelectionModel().getSelectedItem();
        // Check if a reservation is selected
if (selectedReservation == null) {
    // Show alert if no reservation is selected
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une réservation à supprimer !");
    alert.showAndWait();
    return; // Stop the method execution
    
}

ReservationService reservationService = new ReservationService();
try {
    reservationService.supprimer(selectedReservation);
} catch (SQLException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}

// Refresh the list of reservations displayed in the table view
List<Reservation> reservations = null;
try {
    reservations = reservationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}
affichage_res.setItems(FXCollections.observableList(reservations));
        
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
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}

// Refresh the list of reservations displayed in the table view
List<Reservation> reservations = null;
try {
    reservations = reservationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(ReservationController.class.getName()).log(Level.SEVERE, null, ex);
}
affichage_res.setItems(FXCollections.observableList(reservations));

        
    }

    @FXML
    private void selection(MouseEvent event) {
    }
    
}
