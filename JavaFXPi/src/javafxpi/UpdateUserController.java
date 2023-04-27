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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class UpdateUserController implements Initializable {

    @FXML
    private TextField emailLabel;
    @FXML
    private TextField unLabel;
    @FXML
    private TextField ageLabel;
    @FXML
    private TextField idLabel;
    @FXML
    private Button updateBtn;
    
    UserService us = new UserService();
    User u = new User();
    @FXML
    private TextField passLabel;
    @FXML
    private TextField addressLabel;
    @FXML
    private TextField phoneLabel;
    @FXML
    private Button cancelBtn;
    
    
    public void setUser(User c) {
        idLabel.setText(Integer.toString(c.getId()));
        unLabel.setText(c.getUserName());
        emailLabel.setText(c.getEmail());
        ageLabel.setText(Integer.toString(c.getAge()));
        passLabel.setText(c.getPassword());
        phoneLabel.setText(Integer.toString(c.getPhone()));
        addressLabel.setText(c.getAddress());
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateBtnOnAction(ActionEvent event) throws SQLException, IOException {
        int id = Integer.parseInt(idLabel.getText());
        int phone = Integer.parseInt(phoneLabel.getText());
        
        u.setEmail(emailLabel.getText());
        u.setUserName(unLabel.getText());
        u.setPassword(passLabel.getText());
        u.setAddress(addressLabel.getText());
        u.setPhone(phone);
        u.setId(id);
        
        
        us.modifier(u);
        Parent root = FXMLLoader.load(getClass().getResource("AdminUserPage.fxml"));
            Stage stage = (Stage) updateBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void cancelBtnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminUserPage.fxml"));
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
