/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

//import org.controlsfx.control.Notifications;

import entities.Reservation;
import entities.Station;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import jdk.nashorn.api.scripting.JSObject;
import services.ReservationService;
import services.StationService;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class StationController implements Initializable {

    @FXML
    private TextField cap;
    @FXML
    private TextField localisation;
    @FXML
    private TextField mod_cap;
    @FXML
    private TextField mod_loc;
    @FXML
    private TableView<Station> affichage_Stat;
    @FXML
    private TableColumn<Station, ?> col_ids;
    @FXML
    private TableColumn<Station, ?> col_cap;
    @FXML
    private TableColumn<Station, String> col_loc;
    @FXML
    private Button pb_mod_stat;
    @FXML
    private Button pb_supp_stat;
    @FXML
    private Button pb_ajoutStat;

    
      @FXML
    private WebView mapView;
    @FXML
    private Button pb_afficher;
    @FXML
    private Button pb_res;
    @FXML
    private Button pb_stat;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
    
    // Create an instance of the VeloService class
    StationService StationService = new StationService();
    List<Station> Stat = StationService.afficher();
    affichage_Stat.setItems(FXCollections.observableList(Stat));


// Set the cell value factories for the columns
col_ids.setCellValueFactory(new PropertyValueFactory<>("id"));
col_cap.setCellValueFactory(new PropertyValueFactory<>("capacite"));
col_loc.setCellValueFactory(new PropertyValueFactory<>("localisation"));


} catch (SQLException ex) {
    Logger.getLogger(StationController.class.getName()).log(Level.SEVERE, null, ex);
}


//map
    WebEngine webEngine = mapView.getEngine();
        webEngine.load("https://www.google.com/maps/place/Tunisia");
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this);
                webEngine.executeScript("var map = new google.maps.Map(document.getElementById('map'), {zoom: 8, center: {lat: 0, lng: 0}});");
                this.updateMap(0,0);
            }
        });
            
    
       
    }    

     

    @FXML
    private void ajouterStat(ActionEvent event) throws SQLException{
        
        
                 Station s = new Station();
                 
               
    String loc = localisation.getText();
    int capa = Integer.parseInt(cap.getText());

    // Check if debut is before fin
    

    s.setCapacite(capa);
    s.setLocalisation(String.valueOf(loc));
   
    StationService StationService = new StationService();
 Station ss = new Station(capa, loc);
            StationService.ajouter(ss);    
    List<Station> stations = null;
    
        stations = StationService.afficher();
    
    affichage_Stat.setItems(FXCollections.observableList(stations));
            
    
    
   // Notifications not=Notifications.create()
     //           .title("Succé")
       //         .text("")
         //       .hideCloseButton()
           //     .position(Pos.TOP_RIGHT);
       // not.darkStyle();
        //not.showInformation();
          
    }

    
    
    @FXML
    private void modifierStat(ActionEvent event) {
        Station selectedStation = affichage_Stat.getSelectionModel().getSelectedItem();
// Check if a reservation is selected
if (selectedStation == null) {
    // Show alert if no reservation is selected
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une station à modifier !");
    alert.showAndWait();
    return; // Stop the method execution
}

 String loc = localisation.getText();
    int capa = Integer.parseInt(cap.getText());


// Check if debut is before fin
if (cap == null || localisation == null ) {
    // Show alert if debut is after fin or if dates are not selected
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("hhhh !");
    alert.showAndWait();
    return; // Stop the method execution
}

// Update the selected reservation with the modified values
selectedStation.setCapacite(capa);
selectedStation.setLocalisation(String.valueOf(loc));


    


    StationService StationService = new StationService();
try {
    StationService.modifier(selectedStation);
} catch (SQLException ex) {
    Logger.getLogger(StationController.class.getName()).log(Level.SEVERE, null, ex);
}

// Refresh the list of stations displayed in the table view
List<Station> stations = null;
try {
    stations = StationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(StationController.class.getName()).log(Level.SEVERE, null, ex);
}
affichage_Stat.setItems(FXCollections.observableList(stations));



       




    }

    void selection(MouseEvent event) {

    }

    @FXML
    private void supp_stat(ActionEvent event) {
          Station selectedStat = affichage_Stat.getSelectionModel().getSelectedItem();
        // Check if a reservation is selected
if (selectedStat == null) {
    // Show alert if no reservation is selected
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez sélectionner une réservation à supprimer !");
    alert.showAndWait();
    return; // Stop the method execution
}

    StationService StationService = new StationService();
try {
    StationService.supprimer(selectedStat);
} catch (SQLException ex) {
    Logger.getLogger(StationController.class.getName()).log(Level.SEVERE, null, ex);
}

// Refresh the list of reservations displayed in the table view
List<Station> stations = null;
try {
    stations = StationService.afficher();
} catch (SQLException ex) {
    Logger.getLogger(StationController.class.getName()).log(Level.SEVERE, null, ex);
}
affichage_Stat.setItems(FXCollections.observableList(stations));

//map
    WebEngine webEngine = mapView.getEngine();
        webEngine.load("https://www.google.com/maps/place/"+selectedStat.getLocalisation());
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this);
                webEngine.executeScript("var map = new google.maps.Map(document.getElementById('map'), {zoom: 8, center: {lat: 0, lng: 0}});");
                this.updateMap(0,0);
            }
        });
            

    }
    
    
    public void updateMap(double lat, double lng) {
        Platform.runLater(() -> {
            WebEngine webEngine = mapView.getEngine();

        });
    }

    @FXML
    private void afficher_map(ActionEvent event) {
                  Station selectedStatMap = affichage_Stat.getSelectionModel().getSelectedItem();

        
         WebEngine webEngine = mapView.getEngine();
        webEngine.load("https://www.google.com/maps/place/"+selectedStatMap.getLocalisation());
        webEngine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("java", this);
                webEngine.executeScript("var map = new google.maps.Map(document.getElementById('map'), {zoom: 8, center: {lat: 0, lng: 0}});");
                this.updateMap(0,0);
            }
        });
            
    }

    @FXML
    private void res_int(ActionEvent event) {
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
    private void stat_int(ActionEvent event) {
        
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
    
}
