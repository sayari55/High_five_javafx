/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.User;
import ecovelojava.services.UserService;
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
public class AdminUserPageController implements Initializable {
    
    
    @FXML
    private Label userNamePlaceholder;
    @FXML
    private TableView<User> tableUser;
    @FXML
    private TableColumn<User, Integer> colId;
    @FXML
    private TableColumn<User, String> colEmail;
    @FXML
    private TableColumn<User, Integer> colUserName;
    @FXML
    private TableColumn<User, String> colAge;
    @FXML
    private TableColumn<User, String> colAddress;
    @FXML
    private TableColumn<User, Integer> colPhone;
    @FXML
    private TableColumn<User, Integer> colVerif;
    @FXML
    private TableColumn <User , Button > colEdit;
    @FXML
    private TableColumn <User , Button>  colDelete;
    
    UserService us = new UserService();
    @FXML
    private TableColumn<User, String> colPassword;
    @FXML
    private Button reviewDashBtn;
    @FXML
    private TableColumn<User, Button> colMod;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            

            List<User> User;
            User = us.recuperer();
            ObservableList<User> olp = FXCollections.observableArrayList(User);
            tableUser.setItems(olp);
            colId.setCellValueFactory(new PropertyValueFactory("id"));
            colEmail.setCellValueFactory(new PropertyValueFactory("email"));
            colPassword.setCellValueFactory(new PropertyValueFactory("password"));
            colUserName.setCellValueFactory(new PropertyValueFactory("userName"));
            colAge.setCellValueFactory(new PropertyValueFactory("age"));
            colAddress.setCellValueFactory(new PropertyValueFactory("address"));
            colPhone.setCellValueFactory(new PropertyValueFactory("phone"));
            colVerif.setCellValueFactory(new PropertyValueFactory("verified"));
            
   
            this.delete();
            this.modifier();
            
        } catch (SQLException ex) {
            Logger.getLogger(AdminUserPageController.class.getName()).log(Level.SEVERE, null, ex);
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
                                User test = new User(tableUser.getItems().get(getIndex()).getId());
                                if (us.supprimer(test)) {
                                    tableUser.getItems().remove(getIndex());
                                    tableUser.refresh();
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
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("UpdateUser.fxml"));
                                Parent root = loader.load();
                                UpdateUserController modifier = loader.getController();
                                User c = tableUser.getItems().get(getIndex());
                                 
                                modifier.setUser(c);
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
         
         
             private void access() {

        colDelete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("access");
                        b.setOnAction((event) -> {
                            try {
                                User test = new User(tableUser.getItems().get(getIndex()).getId());
                                if (us.supprimer(test)) {
                                    tableUser.getItems().remove(getIndex());
                                    tableUser.refresh();
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


    @FXML
    private void reviewDashBtnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminReviewPage.fxml"));
            Stage stage = (Stage) reviewDashBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
}
