/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.Avis;
import ecovelojava.services.AvisService;
import ecovelojava.services.UserService;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class AvisPageController implements Initializable {
    

    @FXML
    private Button HomePagebutton;
    @FXML
    private Button userPagebutton;
    @FXML
    private Label scorePlaceholder;
    @FXML
    private Label descriptionPlaceholder;
    @FXML
    private AnchorPane myreviewSection;
    @FXML
    private AnchorPane updateReviewSection;
    @FXML
    private Label reviewTitle;
    @FXML
    private Button updateButton;
    @FXML
    private Button addButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        AvisService as = new AvisService();
        Avis a = new Avis(Session.getLoggedInUser().getId());
        try {
            System.out.println(as.afficher(a));
        } catch (SQLException ex) {
            Logger.getLogger(AvisPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(a.getId() == 0){
            reviewTitle.setText("Add Review");
            scorePlaceholder.setText("You didn't leave a review");
            descriptionPlaceholder.setText("You didn't leave a review");
            updateButton.setVisible(false);
            addButton.setVisible(true);
        }else {
            reviewTitle.setText("Add Review");
            
            int note = a.getNote();
            String noteString = Integer.toString(note);
            
            scorePlaceholder.setText(noteString);
            descriptionPlaceholder.setText(a.getDescription());
            updateButton.setVisible(true);
            addButton.setVisible(false);
        }
    }    

    @FXML
    private void HomePageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
            Stage stage = (Stage) userPagebutton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void userPageAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("UserPage.fxml"));
            Stage stage = (Stage) userPagebutton.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
    
    

    
}
