/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Don;
import entities.DonVelo;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.DonCrud;
import services.DonVeloCrud;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class ModifierDon_VeloController implements Initializable {
    
    int id = 3;
    
    @FXML
    private TextField ModeleInput;
    @FXML
    private TextField EmailInput;
    @FXML
    private TextField NomInput;
    @FXML
    private TextField AgeInput;
    @FXML
    private TextField EtatInput;
    @FXML
    private Button ValiderBtn;
    private Button RetourBtn;
    @FXML
    private Text idDonVelo;

    /**
     * Initializes the controller class.
     */
    
     public void setDonVelo(DonVelo v) {
        idDonVelo.setText(Integer.toString(v.getId()));
        NomInput.setText(v.getNom());
        EmailInput.setText(v.getEmail());
        ModeleInput.setText(v.getModele());
        AgeInput.setText(Integer.toString(v.getAge()));
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        ValiderBtn.setOnAction(event -> {
        
        try {
            DonVeloCrud ps = new DonVeloCrud(); 
          ps.modifier(id,23,"modele" , "etat", "mail@mail" , "basskouta" );
           FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherDon_Velo.fxml"));
            Parent root = loader.load();
            
            Scene scene = ValiderBtn.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Modifier_DonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        });
        

    

    
    
}

    @FXML
    private void validerBtnOnAction(ActionEvent event) {
    }
}

