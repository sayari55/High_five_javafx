/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gestionreservation;

import entities.Station;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.StationService;

/**
 *
 * @author khali
 */
public class BackK extends Application {
    
    @Override
  
    public void start(Stage stage){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(BackK.class.getResource("../gui/Reservation.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
            stage.setTitle("Station!");
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            System.out.println("Error" + ex.getMessage());        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
      //  StationService ss =new StationService();
      //  Station s =new Station(18,50,"Marsa");
        //ss.ajouter(s);
        //System.out.println(ss.afficher());        
        
        
        launch(args);
    }
    
}
