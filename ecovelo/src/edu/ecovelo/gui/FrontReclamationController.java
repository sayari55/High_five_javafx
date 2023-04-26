/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.gui;

import edu.ecovelo.entities.reclamation;
import edu.ecovelo.services.ReclamationService;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

/**
 * FXML Controller class
 *
 * 
 */
public class FrontReclamationController implements Initializable {
  private static final List<String> MAUVAIS_MOTS = Arrays.asList("bd1", "bd2", "bd3");
    @FXML
    private TextField text_nom;
    @FXML
    private TextField text_email;
    @FXML
    private TextField text_sujet;
    @FXML
    private TextField text_description;
    
    @FXML
    private Button envoyer_reclamation;
    @FXML
    private Button MesReclamations;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void envoyer(ActionEvent event) {
         if (text_nom.getText().isEmpty() || text_email.getText().isEmpty() || text_sujet.getText().isEmpty()
                || text_description.getText().isEmpty()
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
        if (!text_email.getText().matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Format email incorrect");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez saisir un email valide !");
            alert.showAndWait();
            return;
        }
         String nom = text_nom.getText();
         String email = text_email.getText();
         String sujet = text_sujet.getText();
         String description = text_description.getText();
          for (String mauvaisMot : MAUVAIS_MOTS) {
            if (description.toLowerCase().contains(mauvaisMot)) {
              
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Erreur");
                alert.setHeaderText("La description contient des mots interdits");
                alert.showAndWait();
                return;
            }
        }
         String etat = "en cours";
    
         reclamation rec = new reclamation(nom, email, sujet, description, etat);
          ReclamationService RecService = new ReclamationService();
          RecService.insert(rec);
        text_nom.clear();
        text_email.clear();
        text_sujet.clear();
        text_description.clear();
       
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("reclamtion ajouté avec succès");
        alert.showAndWait();
    }

    @FXML
    private void MesRec(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("mesreclamation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}
