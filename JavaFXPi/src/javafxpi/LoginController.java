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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class LoginController implements Initializable {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private ImageView lockImageView;
    @FXML
    private Label loginMessageLabel;
    @FXML
    private Button signupButton;
    @FXML
    private Button loginButton;
    @FXML
    private Button forgotPasswordBtn;


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
    private void loginButtonOnAction(ActionEvent event) throws IOException {
        if(usernameField.getText().isBlank() == false && passwordField.getText().isBlank() == false){
            
            UserService user = new UserService();
        
            User u =  user.Login(usernameField.getText(), passwordField.getText());
        
            System.out.println(u);
            Session.setLoggedInUser(u);
        if(u.getId()!=0){

            System.out.println("Valid account");
            
            
            if(u.getRoles().contains("ROLE_ADMIN")){
                System.out.println(u.getRoles());
//                

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You are an admin");
                alert.setContentText("You logged in with an admin account , you will be redirected to the admin dashboard");

                alert.showAndWait();
                
                Parent root = FXMLLoader.load(getClass().getResource("AvisPage.fxml"));
                Scene scene = new Scene(root);

                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(scene);
                window.show();
            }else{
            
            
            Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) loginButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();}

        }
        else {
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Invalid Credentials");

                alert.showAndWait();
        }
        }else{
            loginMessageLabel.setText("Please enter your email and password");
        }
   
    }
    


    @FXML
    private void signupButtonAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Stage stage = (Stage) signupButton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void passwordBtnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("ForgotPage.fxml"));
            Stage stage = (Stage) forgotPasswordBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    
    
    
    
}
