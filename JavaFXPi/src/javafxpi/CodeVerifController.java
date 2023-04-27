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
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class CodeVerifController implements Initializable {
    
    UserService us = new UserService();

    @FXML
    private ImageView brandingImageView;
    @FXML
    private Button sendBtn;
    @FXML
    private TextField verificationCode;
    @FXML
    private TextField emailInput;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Page el code verif");
        
        
    }    

    @FXML
    private void sendBtnOnAction(ActionEvent event) throws SQLException, IOException {
        User u = new User(0000, emailInput.getText());
        System.out.println(us.afficherByEmail(u));
        User test = us.afficherByEmail(u);

        String verif = verificationCode.getText();
        if(Integer.parseInt(verif) == test.getReset_code()){
            System.out.println(test.getReset_code());
            System.out.println(verif);
            System.out.println("It matches");
            Parent root = FXMLLoader.load(getClass().getResource("ChangePass.fxml"));
            Stage stage = (Stage) sendBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }else{
            System.out.println("Doesn't match");
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("The verification code you sent is wrong");

                alert.showAndWait();
        }
        
        
    }
    
    
}
