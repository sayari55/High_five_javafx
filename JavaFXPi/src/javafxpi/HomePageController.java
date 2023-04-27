/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class HomePageController implements Initializable {

    @FXML
    private Button userPagebutton;
    @FXML
    private Button reviewPageButton;
    @FXML
    private Label userNamePlaceholder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        userNamePlaceholder.setText((Session.getLoggedInUser().getUserName()));
    }    

    @FXML
    private void userPageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
            Stage stage = (Stage) userPagebutton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void reviewPageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AvisPage.fxml"));
            Stage stage = (Stage) userPagebutton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    
    
}
