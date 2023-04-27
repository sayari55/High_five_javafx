/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.Avis;
import ecovelojava.entities.User;
import ecovelojava.services.AvisService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class AdminReviewPageController implements Initializable {

    @FXML
    private Label userNamePlaceholder;
    @FXML
    private TableView<Avis> tableAvis;
    @FXML
    private TableColumn<Avis, Integer> colId;
    @FXML
    private TableColumn<Avis, Integer> colUserId;
    @FXML
    private TableColumn<Avis, String> colDescription;
    @FXML
    private TableColumn<Avis, Integer> colNote;
    @FXML
    private TableColumn<Avis, Button> colEdit;
    @FXML
    private TableColumn<Avis, Button> colDelete;
    
    AvisService as = new AvisService();
    @FXML
    private Button userDashBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            //        try {
            // TODO

            List<Avis> Avis;
            Avis = as.recuperer();
            ObservableList<Avis> olp = FXCollections.observableArrayList(Avis);
            tableAvis.setItems(olp);
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            colUserId.setCellValueFactory(new PropertyValueFactory("userId"));
            colDescription.setCellValueFactory(new PropertyValueFactory("description"));
            colNote.setCellValueFactory(new PropertyValueFactory("note"));
            
   
            this.delete();
            this.modifier();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminReviewPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
     private void delete() {

        colDelete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            try {
                                Avis test = new Avis(tableAvis.getItems().get(getIndex()).getId(),tableAvis.getItems().get(getIndex()).getUserId() );
                                if (as.supprimer(test)) {
                                    tableAvis.getItems().remove(getIndex());
                                    tableAvis.refresh();
                                }
                            } catch (SQLException ex) {
                                Logger.getLogger(AdminUserPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
        
        
        
         
       
         
         private void modifier() {
            colEdit.setCellFactory((param) -> {
            return new TableCell() {
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("Update");
                        b.setOnAction((event) -> {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateAvis.fxml"));
                                Parent root = loader.load();
                                UpdateAvisController modifier = loader.getController();
                                Avis c = tableAvis.getItems().get(getIndex());
                                 
                                modifier.setAvis(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(AdminUserPageController.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });

    
}

    @FXML
    private void userDashBtnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminUserPage.fxml"));
            Stage stage = (Stage) userDashBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
