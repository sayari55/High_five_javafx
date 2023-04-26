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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author benza
 */
public class MesreclamationController implements Initializable {

   
    @FXML
    private TableView<reclamation> tablemesreclamation;
    @FXML
    private TableColumn email;
    @FXML
    private TableColumn sujet;
    @FXML
    private TableColumn description;
    @FXML
    private TableColumn date_rec;
    @FXML
    private TableColumn etat;
    @FXML
    private TableColumn nom;
    @FXML
    private TextField description_text;
    @FXML
    private TableColumn id_rec;
    @FXML
    private Button retourner;
    @FXML
    private Button modifier;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        id_rec.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_rec.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         ReclamationService RecService = new ReclamationService();
      List<reclamation> recList = RecService.readAll();

        // affiche les données dans le tableau
        tablemesreclamation.getItems().setAll(recList);
    }  
     public void initTable() {
         id_rec.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
      nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_rec.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         ReclamationService RecService = new ReclamationService();
      List<reclamation> userList = RecService.readAll();

        // affiche les données dans le tableau
        tablemesreclamation.getItems().setAll(userList);

    }

    @FXML
    private void retournerfront(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("frontReclamation.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modifierec(ActionEvent event) {
       reclamation selectedRec = tablemesreclamation.getSelectionModel().getSelectedItem();
edu.ecovelo.services.ReclamationService recService = new edu.ecovelo.services.ReclamationService();
        // 2. Afficher un message d'erreur si aucun utilisateur n'est sélectionné
        if (selectedRec == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No reclamation selected");
            alert.setHeaderText(null);
            alert.setContentText("Please select a reclamation in the table.");
            alert.showAndWait();
            return;
        }
     Date dateReclamation = selectedRec.getDate_reclamation();
                String date1 = String.valueOf(dateReclamation);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate dateReclamation1 = LocalDate.parse(date1, formatter);
                // Convert the date to a LocalDate object

                LocalDate dateSysteme = LocalDate.now();
                if (dateSysteme.isAfter(dateReclamation1)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setHeaderText("La réclamation ne peut pas être modifiée");
                    alert.setContentText("La date limite pour modifier la réclamation est dépassée");
                    alert.showAndWait();
                    return;
                }

        // 3. Demander confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm modification");
        alert.setHeaderText(null);
        alert.setContentText("Are you sure you want to modify the selected reclamation?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() != ButtonType.OK) {
            return;
        }

        // 4. Récupérer la nouvelle valeur de id_role depuis le ComboBox
        String desc = description_text.getText();
       

        // 5. Mettre à jour la propriété id_role de l'utilisateur sélectionné
        selectedRec.setDescription(desc);

        // 6. Mettre à jour l'utilisateur dans la base de données
        recService.updateRec(selectedRec);

        // 7. Actualiser la liste des utilisateurs affichée dans le tableau
        List<reclamation> recList = recService.readAll();
        tablemesreclamation.getItems().setAll(recList);
    }

    

   
}
