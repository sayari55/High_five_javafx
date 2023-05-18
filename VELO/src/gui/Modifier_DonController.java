/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Don;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import services.DonCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Modifier_DonController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField type;
    @FXML
    private TextField motif;
    @FXML
    private Button modifier_don;
    @FXML
    private Text idDon;
    @FXML
    private Label userNamePlaceholder;

    /**
     * Initializes the controller class.
     */
    public void setDon(Don c) {
        idDon.setText(Integer.toString(c.getId()));
        nom.setText(c.getNom());
        email.setText(c.getEmail());
        type.setText(c.getType());
         motif.setText(c.getMotif());
        
        
        
       

    }
            
            
            
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        modifier_don.setOnAction(event -> {
        
        try {
            DonCrud ps = new DonCrud(); 
          ps.modifier(nom.getText(), email.getText(), type.getText(), motif.getText() , Integer.parseInt(idDon.getText())  );
           FXMLLoader loader = new FXMLLoader(getClass().getResource("Afficher_Don.fxml"));
            Parent root = loader.load();
            
            Scene scene = modifier_don.getScene();
            scene.setRoot(root);
        } catch (IOException ex) {
            Logger.getLogger(Modifier_DonController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
        
        
        });
        
        
      
        
    }
    
    
   
    
    
    
    
    }
