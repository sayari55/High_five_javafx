/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.gui;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.ecovelo.entities.reclamation;
import edu.ecovelo.services.ReclamationService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import edu.ecovelo.gui.Ecovelo;
import edu.ecovelo.services.ReponseService;
import edu.ecovelo.utils.MyConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author benza
 */
public class BackReclamationController implements Initializable {

    @FXML
    private TableView<reclamation> tablemesreclamation;
    @FXML
    private TableColumn nom;
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
    private Button repondre;
    @FXML
    private TableColumn id;
    @FXML
    private Button mes_reponse;
    @FXML
    private Button btnDelete;
    @FXML
    private TextField rech;
    @FXML
    private Button genererpdf;
    private Connection conn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         conn = MyConnection.getInstance().getCnx();
        id.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
           nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_rec.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         ReclamationService RecService = new ReclamationService();
      List<reclamation> recList = RecService.readAllAdmin();

        // affiche les données dans le tableau
        tablemesreclamation.getItems().setAll(recList);
    }    

    @FXML
    private void repond(ActionEvent event) throws IOException {
        

          if (tablemesreclamation.getSelectionModel().getSelectedItem() == null) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Erreur");
    alert.setHeaderText("Aucune reclamation selectionée");
    alert.setContentText("Aucune reclamation selectionée");
    alert.showAndWait();
} else if (tablemesreclamation.getSelectionModel().getSelectedItem() != null){
    // Récupérer la réclamation correspondante
   
    reclamation r = tablemesreclamation.getSelectionModel().getSelectedItem();
    Ecovelo.id_recselect = r.getId_reclamation();
              System.out.println(Ecovelo.id_recselect);
     Parent root = FXMLLoader.load(getClass().getResource("reponse.fxml"));
    Scene scene = new Scene(root);
    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    stage.setScene(scene);
    stage.show();

    // passer à l'interface reponse
    
}


 
}

    @FXML
    private void mesrp(ActionEvent event) throws IOException {
          Parent root = FXMLLoader.load(getClass().getResource("touslesreponse.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void DeleteHandler(ActionEvent event) {
        reclamation selectedreclamation = tablemesreclamation.getSelectionModel().getSelectedItem();

// Vérifier que l'utilisateur a bien sélectionné un emploi
        if (tablemesreclamation == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText("Aucun emploi sélectionné");
            alert.setContentText("Veuillez sélectionner un emploi à supprimer.");
            alert.showAndWait();
            return;
        }

// Demander confirmation à l'utilisateur
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText("Voulez-vous vraiment supprimer cet emploi ?");
//alert.setContentText("Titre : " + tablereclamation.getTitre() + "\nDescription : " + tablereclamation.getDescription());

        if (alert.showAndWait().get() == ButtonType.OK) {
            // Supprimer l'emploi de la base de données
           ReclamationService recService = new ReclamationService();
           reclamation r = tablemesreclamation.getSelectionModel().getSelectedItem();
           
//    emploiDAO.deleteReclamation(tablereclamation);
            recService.delete(r.getId_reclamation());

            // Supprimer l'emploi de la liste observable et de la table
            // Afficher un message de confirmation
            Alert confirmationAlert = new Alert(Alert.AlertType.INFORMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Emploi supprimé avec succès");
//    confirmationAlert.setContentText("Titre : " + selectedEmploi.getTitre() + "\nDescription : " + selectedEmploi.getDescription());
            confirmationAlert.showAndWait();

             id.setCellValueFactory(new PropertyValueFactory<>("id_reclamation"));
           nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        sujet.setCellValueFactory(new PropertyValueFactory<>("sujet"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_rec.setCellValueFactory(new PropertyValueFactory<>("date_reclamation"));
        etat.setCellValueFactory(new PropertyValueFactory<>("etat"));
         ReclamationService RecService = new ReclamationService();
      List<reclamation> recList = RecService.readAllAdmin();

        // affiche les données dans le tableau
        tablemesreclamation.getItems().setAll(recList);

        }
    }

    @FXML
    private void recherche(KeyEvent event) {
            ReclamationService recService = new ReclamationService();
          if (event.getCode() == KeyCode.ENTER) {
             String nom = rech.getText();
            reclamation searcheddiag = recService.readBynom(nom);
            if (searcheddiag != null) {
                tablemesreclamation.getItems().setAll(searcheddiag);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("No reclamation found");
                alert.setHeaderText(null);
                alert.setContentText("No reclamation found with the given name.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void pdfcreate(ActionEvent event) {
           com.itextpdf.text.Document document = new com.itextpdf.text.Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("reclamation.pdf"));
            document.open();
            PdfPTable table = new PdfPTable(3); // 3 columns

            // Add table headers
            table.addCell("id_reclamation");
            table.addCell("nom");
            table.addCell("email");
            conn = MyConnection.getInstance().getCnx();
            // Add table rows from the database
            String query = "SELECT * FROM reclamation";
            ResultSet resultSet = conn.createStatement().executeQuery(query);
            while (resultSet.next()) {
                int id_reclamation = resultSet.getInt("id_reclamation");
                String nom = resultSet.getString("nom");
               String email = resultSet.getString("email");
                table.addCell(Integer.toString(id_reclamation));   //ism les attribut ml base 
                table.addCell(nom);
                table.addCell(email);
            }
            document.add(table);
            document.close();
            JOptionPane.showMessageDialog(null, "Les données des reclamation ont été exportées dans le fichier reclamation.pdf");

            // Open the generated PDF file
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(new File("reclamation.pdf"));
            }
        } catch (FileNotFoundException | DocumentException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Erreur lors de l'exportation des données des reclamation : " + e.getMessage());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Erreur d'entrée/sortie : " + e.getMessage());
        }
        
    }
}
