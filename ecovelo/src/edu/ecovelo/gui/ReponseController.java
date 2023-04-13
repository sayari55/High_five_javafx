/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.gui;

import edu.ecovelo.entities.reclamation;
import edu.ecovelo.entities.reponse;
import edu.ecovelo.services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import edu.ecovelo.gui.Ecovelo;
import edu.ecovelo.services.ReponseService;
/**
 * FXML Controller class
 *
 * @author benza
 */
public class ReponseController implements Initializable {

    @FXML
    private TextField sujet_text;
    @FXML
    private TextField motif_text;
    @FXML
    private Button envoyerreponse;
    @FXML
    private Button retourner;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoirep(ActionEvent event) {
          if (sujet_text.getText().isEmpty() || motif_text.getText().isEmpty() || email.getText().isEmpty() 
                ) {
            // Afficher un message d'alerte
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Champs manquants");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs !");
            alert.showAndWait();
            return;
        }
          String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (!email.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un email valide !");
            alert.showAndWait();
            return;
        }
         String adressemail = email.getText();
         String sujet = sujet_text.getText();
         String motif = motif_text.getText();
         
    
        reponse rep = new reponse(Ecovelo.id_recselect, sujet, motif);
        ReponseService RepService = new ReponseService();
        RepService.insert(rep);
        sujet_text.clear();
        motif_text.clear();
         email.clear();
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("reclamtion ajouté avec succès");
        alert.showAndWait();
        
          
            SendEmailController.sendEmail(adressemail,sujet,motif);
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Succees");
            confirmationAlert.setHeaderText("Mail envoyé avec succès");
      
        }


    @FXML
    private void routour(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("backReclamation.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();
    }
    
}
