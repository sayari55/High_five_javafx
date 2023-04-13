/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.gui;

import edu.ecovelo.entities.reponse;
import edu.ecovelo.services.ReponseService;
import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benza
 */
public class TouslesreponseController implements Initializable {

    @FXML
    private TableView<reponse> tablereponse;
    @FXML
    private TableColumn id_rep;
    @FXML
    private TableColumn id_rec;
    @FXML
    private TableColumn date_rep;
    @FXML
    private TableColumn sujet;
    @FXML
    private TableColumn motif;
    @FXML
    private Button retourner;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         id_rep.setCellValueFactory(new PropertyValueFactory<>("id_reponse"));
        id_rec.setCellValueFactory(new PropertyValueFactory<>("id_reclamation_id"));
        date_rep.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet_reponse"));
        motif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        
         ReponseService RepService = new ReponseService();
      List<reponse> recList = RepService.readAll();

        // affiche les données dans le tableau
        tablereponse.getItems().setAll(recList);
    }    

    @FXML
    private void retour(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("backreclamation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void suppression(ActionEvent event) {
           reponse selectedreponse = tablereponse.getSelectionModel().getSelectedItem();

// Vérifier que l'utilisateur a bien sélectionné un emploi
        if (selectedreponse == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun reponse sélectionné");
            alert.setContentText("Veuillez sélectionner un reponse à supprimer.");
            alert.showAndWait();
            return;
        }

// Demander confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet reponse ?");
//alert.setContentText("Titre : " + tablereclamation.getTitre() + "\nDescription : " + tablereclamation.getDescription());

        if (alert.showAndWait().get() == ButtonType.OK) {
            // Supprimer l'emploi de la base de données
           ReponseService repService = new ReponseService();
           reponse r = tablereponse.getSelectionModel().getSelectedItem();
           int id_supp = r.getId_reponse();
//    emploiDAO.deleteReclamation(tablereclamation);
            repService.delete(id_supp);

            // Supprimer l'emploi de la liste observable et de la table
            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("reponse supprimé avec succès");
//    confirmationAlert.setContentText("Titre : " + selectedEmploi.getTitre() + "\nDescription : " + selectedEmploi.getDescription());
            confirmationAlert.showAndWait();

                  id_rep.setCellValueFactory(new PropertyValueFactory<>("id_reponse"));
        id_rec.setCellValueFactory(new PropertyValueFactory<>("id_reclamation_id"));
        date_rep.setCellValueFactory(new PropertyValueFactory<>("date_reponse"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet_reponse"));
        motif.setCellValueFactory(new PropertyValueFactory<>("motif"));
        
         ReponseService RepService = new ReponseService();
      List<reponse> recList = RepService.readAll();

        // affiche les données dans le tableau
        tablereponse.getItems().setAll(recList);

        }
    }
    
}
