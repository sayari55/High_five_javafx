/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class HomeController implements Initializable {

    @FXML
    private Label factLabel;
    @FXML
    private Button ParArgentbtn;
    @FXML
    private Button Parvelobtn;
    @FXML
    private Button info;
    @FXML
    private Button jeu;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void argent(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("AjouterDon.fxml"));
            Stage stage = (Stage) ParArgentbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void parvelo(ActionEvent event) throws IOException {
        
           Parent root = FXMLLoader.load(getClass().getResource("AjouterDon_Velo.fxml"));
            Stage stage = (Stage) Parvelobtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void infoAction(ActionEvent event) throws IOException {
        
        
         Parent root = FXMLLoader.load(getClass().getResource("RandomVeloFacts.fxml"));
            Stage stage = (Stage) info.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void jeuAction(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
            Stage stage = (Stage) jeu.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    
    
    }
    
