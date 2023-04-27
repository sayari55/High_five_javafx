/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.User;
import ecovelojava.services.UserService;
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
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class ChangePassController implements Initializable {
    UserService us = new UserService();

    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button changeBtn;
    @FXML
    private TextField passwordLabel;
    @FXML
    private TextField emailInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void changeBtnOnAction(ActionEvent event) throws SQLException, IOException {
        User u = new User(0000, emailInput.getText());
        User test = us.afficherByEmail(u);
        
        if(test.getId() != 0){
            String version = "2y";
            int strength = 12; // the strength factor
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength,new SecureRandom(version.getBytes()));
            String hpwd = passwordEncoder.encode(passwordLabel.getText());
            test.setPassword(hpwd);
            us.modifier(test);
            System.out.println("mot de passe modifier");
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Stage stage = (Stage) changeBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            System.out.println("Error");
        }
    }
    
}
