/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.User;
import ecovelojava.services.UserService;
import java.io.IOException;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.net.URL;
import java.sql.SQLException;
import java.util.Random;
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
import utils.JavaMailUtil;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class ForgotPageController implements Initializable {
    
    UserService us = new UserService();

    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField emailField;
    @FXML
    private Button sendBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

//    Random rand = new Random();
//        System.out.println(rand.nextInt(10000));


    
    @FXML
    private void sendBtnOnAction(ActionEvent event) throws IOException, SQLException, Exception {
        Random rand = new Random();
        int test = rand.nextInt(10000);
        User u = new User(test , emailField.getText());
        us.modifierByEmail(u);
       
        String mailText ="You have requested to change your account password, your verification code is : " + test;
        String subject="Change Password request";

        JavaMailUtil.sendMail(emailField.getText(), subject ,mailText);
       
        
        Parent root = FXMLLoader.load(getClass().getResource("CodeVerif.fxml"));
            Stage stage = (Stage) sendBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    

    
}
