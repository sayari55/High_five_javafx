/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package gestionreservation;

import entities.Reservation;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import services.ReservationService;
import utils.MyDB;

/**
 *
 * @author khali
 */
public class FrontK extends Application {
    
    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override

    public void start(Stage stage) throws IOException  {
         FXMLLoader fxmlLoader = new FXMLLoader(FrontK.class.getResource("../gui/ResFront.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 1000);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
       

        launch(args);
    }
    
}
