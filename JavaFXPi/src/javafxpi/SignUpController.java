/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.User;
import ecovelojava.services.UserService;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import utils.DatabaseConnection;
import java.sql.Connection;
import java.sql.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import utils.JavaMailUtil;
import utils.Session;




/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class SignUpController implements Initializable {
    
    UserService us = new UserService();

    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private TextField emailField;
    @FXML
    private TextField addressField;
    @FXML
    private Button signupButton;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button loginButton;
    @FXML
    private TextField usernameField;
    @FXML
    private TextField ageField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField phoneField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        File brandingFile = new File("Images/logo.png");
        Image brandingImage = new Image(brandingFile.toURI().toString());
        brandingImageView.setImage(brandingImage);
        
        File lockFile = new File("Images/lock.png");
        Image lockImage = new Image(lockFile.toURI().toString());
        lockImageView.setImage(lockImage);
    }    

    @FXML
    private void signupButtonOnAction(ActionEvent event) throws IOException, Exception {
        User u = new User();
        
        int x = 0;
        try {
            String userEmail = emailField.getText();
//            String userPassword = passwordField.getText();
            String userPassword = passwordField.getText();
            String version = "2y";
            int strength = 12; // the strength factor
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength,new SecureRandom(version.getBytes()));
            String hashedPassword = passwordEncoder.encode(userPassword);


String userName = usernameField.getText();
            String userAge = ageField.getText();
            String userAddress = addressField.getText();
            String userPhone = phoneField.getText();
            
            
            
            u.setEmail(userEmail);
            u.setPassword(hashedPassword);
            u.setUserName(userName);
            u.setAge(Integer.parseInt(userAge));
            u.setAddress(userAddress);
            u.setPhone(Integer.parseInt(userPhone));
            
//            System.out.println(u);
            if(verif_field() == true && verif_email(u) == true){

            us.ajouter(u);
            System.out.println("user succefully added");
            Session.setLoggedInUser(u);
            
            String mailText = "Account Creation";
            String subject="Thank you for choosing Eco-Velo , You're account has been succefuly created , please wait until and admin verifies your account. ";

            JavaMailUtil.sendMail(userEmail, subject ,mailText);
            
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) signupButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            }else{
                System.out.println("User already exists");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("An Account already exists with this email");

                alert.showAndWait();
            }


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    private void reset () {
            emailField.setText("");
            passwordField.setText("");
            usernameField.setText("");
            phoneField.setText("");
            ageField.setText("");
            addressField.setText("");
        }
    
    private boolean verif_field(){
        int x = 0;
        if(emailField.getText().isBlank()== true || !emailField.getText().contains("@") ){

                emailField.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Invalid Email");

                alert.showAndWait();
            }
            
            if(passwordField.getText().length()< 6 ){

                passwordField.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Password must be longer than 5");

                alert.showAndWait();
            }
            
            
            if(usernameField.getText().length()<6 ){

                usernameField.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("UserName must be longer than 5");

                alert.showAndWait();
            }
            
            if(addressField.getText().length()<6 ){

                addressField.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("Enter a valid address");

                alert.showAndWait();
            }
            
            if(Integer.parseInt(ageField.getText()) <6 ){

                ageField.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
                x++;
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Erreur");
                alert.setContentText("You must be 18 or older");

                alert.showAndWait();
            }
            
            if (phoneField.getText().length() != 8 || !phoneField.getText().matches("[0-9]+")) {

                phoneField.setStyle("-fx-border-color: red ; -fx-border-width: 2px;");
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
    
    private boolean verif_email(User u){
        Connection cnx = DatabaseConnection.getInstance().getCnx();
        String email = "";

        try {

            String req1 = "SELECT * FROM User where email='" + email + "'";


            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(req1);

            while ((rs1.next())) {
                email = rs1.getString("email");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (email != "") {
            System.out.println("email exists ");
            return false;

        } else {
            return true;
        }
    }
    

    @FXML
    private void loginButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) signupButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
