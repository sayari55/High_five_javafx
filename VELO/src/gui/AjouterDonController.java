/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Don;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.DonCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class AjouterDonController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField email;
    @FXML
    private TextField type;
    @FXML
    private Button Ajouter_don;


Connection cnx2 ;
    @FXML
    private Button Retour;
    @FXML
    private TextField motif;
    @FXML
    private Label userNamePlaceholder;
    
    public AjouterDonController() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        Ajouter_don.setOnAction(i  -> {

         if ( "".equals(nom.getText()) || "".equals (email.getText()) || !email.getText().contains("@")
|| "".equals (type.getText()) || "".equals (motif.getText()) || !isNumeric(motif.getText()))  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez remplir tous les champs correctement!");

                alert.show();

            } else {
             

                try {

                    DonCrud ajout = new DonCrud();
                   Don in = new Don(nom.getText(), email.getText(), type.getText() , motif.getText() );
                    //verification de l'unicite par le nom de la demande le budget la description et la date limite 
                    String sql = "SELECT * FROM don WHERE nom = ? AND email = ? AND type = ? AND motif = ? ";
                    PreparedStatement stmt;
                    stmt = cnx2.prepareStatement(sql);
                    stmt.setString(1, in.getNom());
                    stmt.setString(2, in.getEmail());
                    stmt.setString(3, in.getType());
                    stmt.setString(4, in.getMotif());

                    
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {
                        
                        
                        

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Don existe deja!");

                        alert.show();
                        return; 
                        
                    } else {

                        ajout.ajouter(in);
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Don ajoutée avec succés!");

                        alert.show();

                    }

                } 
                catch (SQLException ex) {
                    Logger.getLogger(AjouterDonController.class.getName()).log(Level.SEVERE, null, ex);
                }

//                    // si elle n'existe pas on procede a l ajout 
//                    ajoutDemandeService.ajouterDemandeService(ds);
//
//            Publication p = new Publication(Integer.parseInt(proprietaire.getText()), libelle.getText(),datePub.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),Description.getText(),cat.getValue());
//
//            PublicationCrud test = new PublicationCrud() ;
            }
        }
        );
        
        
        
        
      
        
        
        
        
        
        
        
        
    }    
    private boolean isNumeric(String str) {
if (str == null || str.length() == 0) {
return false;
}
try {
Double.parseDouble(str);
return true;
} catch (NumberFormatException e) {
return false;
}
}

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
         Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) Retour.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }






}