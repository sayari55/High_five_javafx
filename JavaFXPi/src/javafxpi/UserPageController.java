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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class UserPageController implements Initializable {
    
        UserService us = new UserService();


    @FXML
    private Button HomePagebutton;
    @FXML
    private Button reviewPagebutton;
    @FXML
    private Label UserNamePlaceholder;
    @FXML
    private Label EmailPlaceholder;
    @FXML
    private Label AgePlaceholder;
    @FXML
    private Label AddressPlaceholder;
    @FXML
    private Label PhonePlaceholder;

    /**
     * Initializes the controller class.
     */
    
    
    int age = Session.getLoggedInUser().getAge();
    String ageString = Integer.toString(age);
    
    
    
    int phone = Session.getLoggedInUser().getPhone();
    String phoneString = Integer.toString(phone);
    @FXML
    private Label NavBarPlaceholder;
    @FXML
    private TextField userNameLabel;
    @FXML
    private TextField emailLabel;
    @FXML
    private TextField phoneLabel;
    @FXML
    private TextField addressLabel;
    @FXML
    private TextField ageLabel;
    @FXML
    private TextField passwordLabel;
    @FXML
    private Button updateButton;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
                UserNamePlaceholder.setText((Session.getLoggedInUser().getUserName()));
                NavBarPlaceholder.setText(Session.getLoggedInUser().getUserName());
                EmailPlaceholder.setText((Session.getLoggedInUser().getEmail()));
                AgePlaceholder.setText(ageString);
                AddressPlaceholder.setText((Session.getLoggedInUser().getAddress()));
                PhonePlaceholder.setText(phoneString);
                
    }    

    @FXML
    private void HomePageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) HomePagebutton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void reviewPageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AvisPage.fxml"));
            Stage stage = (Stage) reviewPagebutton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void updateButtonOnClick(ActionEvent event) {
        User u = new User();
        
        int x = 0;
        try {
            String userEmail = emailLabel.getText();
            String userPassword = passwordLabel.getText();
            String userName = userNameLabel.getText();
            String userAge = ageLabel.getText();
            String userAddress = addressLabel.getText();
            String userPhone = phoneLabel.getText();
            
            
            u.setId(Session.getLoggedInUser().getId());
            if(!userEmail.isBlank()){
            u.setEmail(userEmail);
            }else{
                u.setEmail(Session.getLoggedInUser().getEmail());
            }
            
            if(!userPassword.isBlank()){
            u.setPassword(userPassword);
            }else{
                u.setPassword(Session.getLoggedInUser().getPassword());
            }
            
            if(!userName.isBlank()){
            u.setUserName(userName);
            }else{
                u.setUserName(Session.getLoggedInUser().getUserName());
            }
            
            if(!userAge.isBlank()){
            u.setAge(Integer.parseInt(userAge));
            }else{
                u.setAge(Session.getLoggedInUser().getAge());
            }
            
            if(!userAddress.isBlank()){
            u.setAddress(userAddress);
            }else{
                u.setAddress(Session.getLoggedInUser().getAddress());
            }
            
            if(!userPhone.isBlank()){
            u.setPhone(Integer.parseInt(userPhone));
            }else{
                u.setPhone(Session.getLoggedInUser().getAge());
            }

            us.modifier(u);
            System.out.println("user succefully modified");

              Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Update");
                alert.setContentText("Your Info has been updated");
                alert.showAndWait();
                reset();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            reset();
        }
        
    }
    
    
     private boolean verif_field(){
        int x = 0;
        if(emailLabel.getText().isBlank()== true || !emailLabel.getText().contains("@") ){

                emailLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Invalid Email");

                alert.showAndWait();
            }
            
            if(passwordLabel.getText().length()< 6 ){

                passwordLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Password must be longer than 5");

                alert.showAndWait();
            }
            
            
            if(userNameLabel.getText().length()<6 ){

                userNameLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("UserName must be longer than 5");

                alert.showAndWait();
            }
            
            if(addressLabel.getText().length()<6 ){

                addressLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Enter a valid address");

                alert.showAndWait();
            }
            
            if(Integer.parseInt(ageLabel.getText()) <6 ){

                ageLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("You must be 18 or older");

                alert.showAndWait();
            }
            
            if (phoneLabel.getText().length() != 8 || !phoneLabel.getText().matches("[0-9]+")) {

                phoneLabel.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("You must be 18 or older");

                alert.showAndWait();
            }
            
            if( x == 0 ){
                return true;
            }else{
                return false;
            }
        
    }
     
     private void reset () {
            userNameLabel.setText("");
            emailLabel.setText("");
            addressLabel.setText("");
            ageLabel.setText("");
            passwordLabel.setText("");
        }
    
    
}
