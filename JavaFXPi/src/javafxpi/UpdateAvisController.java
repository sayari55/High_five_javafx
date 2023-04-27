/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.Avis;
import ecovelojava.services.AvisService;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khaliljebali
 */
public class UpdateAvisController implements Initializable {
    
    AvisService as = new AvisService();
    @FXML
    private TextField idLabel;
    @FXML
    private TextField userIdLabel;
    @FXML
    private TextField noteLabel;
    @FXML
    private TextArea descriptionLabel;
    @FXML
    private Button updateBtn;

    /**
     * Initializes the controller class.
     */
    
    Avis a = new Avis();
    @FXML
    private Button cancelBtn;
    
    
    public void setAvis(Avis c) {
        idLabel.setText(Integer.toString(c.getId()));
        userIdLabel.setText(Integer.toString(c.getUserId()));
        noteLabel.setText(Integer.toString(c.getNote()));
        descriptionLabel.setText(c.getDescription());
        
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updateBtnOnAction(ActionEvent event) throws SQLException, IOException {
        int id = Integer.parseInt(idLabel.getText());
        int note = Integer.parseInt(noteLabel.getText());
        int user_id = Integer.parseInt(userIdLabel.getText());
        
        a.setNote(note);
        a.setDescription(descriptionLabel.getText());
        a.setUserId(user_id);
        a.setId(id);
        
        
        as.modifier(a);
        Parent root = FXMLLoader.load(getClass().getResource("AdminReviewPage.fxml"));
            Stage stage = (Stage) updateBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void cancelBtnOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminReviewPage.fxml"));
            Stage stage = (Stage) cancelBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
