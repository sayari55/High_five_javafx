/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Don;
import entities.DonVelo;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.DonVeloCrud;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class AfficherDon_VeloController implements Initializable {

    @FXML
    private TableView<DonVelo> invitetv;
    @FXML
    private TableColumn<DonVelo, Integer> id;
    @FXML
    private TableColumn<DonVelo, String> nom;
    @FXML
    private TableColumn<DonVelo, String> email;
    @FXML
    private TableColumn<DonVelo, String> modele;
    @FXML
    private TableColumn<DonVelo, String> etat;
    @FXML
    private TableColumn<DonVelo, Integer> age;
    @FXML
    private TableColumn<DonVelo, Button> delete;
    @FXML
    private TableColumn<DonVelo, Button> modifier;
    private Button Retour;
    
    DonVeloCrud dv = new DonVeloCrud();
    @FXML
    private Label userNamePlaceholder;
    @FXML
    private Button DonparArgent;
    @FXML
    private Button DonparVelo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          List<DonVelo> DonVelo;
            DonVelo = dv.afficher();
            ObservableList<DonVelo> olp = FXCollections.observableArrayList(DonVelo);
            invitetv.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id"));
            nom.setCellValueFactory(new PropertyValueFactory("nom"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            modele.setCellValueFactory(new PropertyValueFactory("modele"));
            etat.setCellValueFactory(new PropertyValueFactory("etat"));
            age.setCellValueFactory(new PropertyValueFactory("age"));
            this.delete();
            this.modifier();
            
           
            
    }    
    
    
     private boolean showConfirmationDialog(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
}

    
    
    
    
     private void delete() {

        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                              if (showConfirmationDialog("Confirmation de suppression", "Voulez-vous vraiment supprimer cet élément ?")) {
                            if (dv.supprimer(invitetv.getItems().get(getIndex()).getId())) {
                                invitetv.getItems().remove(getIndex());
                                invitetv.refresh();
                            }
                              }
                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
private void modifier() {
            modifier.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("modifier");
                        b.setOnAction((event) -> {
                              if (showConfirmationDialog("Confirmation de modification", "Voulez-vous vraiment modifier cet don ?"))
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierDon_Velo.fxml"));
                                Parent root = loader.load();
                                gui.ModifierDon_VeloController modifier = loader.getController();
                                DonVelo c = invitetv.getItems().get(getIndex());
                                 
                                modifier.setDonVelo(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AfficherDon_VeloController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }

    @FXML
    private void btndonArgent(ActionEvent event) throws IOException {
        
        
           Parent root = FXMLLoader.load(getClass().getResource("Afficher_Don.fxml"));
            Stage stage = (Stage) DonparArgent.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void btnDonVelo(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AfficherDon_Velo.fxml"));
            Stage stage = (Stage) DonparVelo.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
                 
}
