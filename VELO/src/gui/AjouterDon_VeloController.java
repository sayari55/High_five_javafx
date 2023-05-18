/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import com.sun.java.swing.plaf.motif.resources.motif;
import entities.Don;
import entities.DonVelo;
import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JPanel;
import org.json.JSONObject;
import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;
import services.DonCrud;
import services.DonVeloCrud;
import utils.MyConnection;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class AjouterDon_VeloController implements Initializable {

    @FXML
    private TextField nomInput;
    @FXML
    private TextField mailInput;
    @FXML
    private TextField modeleInput;
    @FXML
    private TextField ageInput;
    @FXML
    private TextField etatInput;
    @FXML
    private Button validerBtn;
    @FXML
    private Button retourBtn;
    
    Connection cnx2 ;
    @FXML
    private SwingNode swingNode;
    @FXML
    private Label userNamePlaceholder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
           cnx2 = MyConnection.getInstance().getCnx();
           
           
           
           //map
           
           
            JMapViewer mapViewer = new JMapViewer();

        // Set the initial position of the map
        mapViewer.setDisplayPosition(new Coordinate(34.8516, 10.7605), 7);

// Add a mouse listener to the mapViewer
        mapViewer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the coordinates of the clicked point
                //Coordinate clickedPoint = mapViewer.getPosition(e.getPoint());
                ICoordinate clickedPoint = mapViewer.getPosition(e.getPoint());
                //org.openstreetmap.gui.jmapviewer.Coordinate clickedPoint = mapViewer.getPosition(e.getPoint());

                // Do something with the clicked coordinates, e.g. create a marker
                MapMarkerDot marker = new MapMarkerDot((Coordinate) clickedPoint);
                mapViewer.addMapMarker(marker);

                // Perform reverse geocoding to get the name of the country
                String countryName = null;
                try {
                    String urlString = "https://nominatim.openstreetmap.org/reverse?format=jsonv2&lat="
                            + clickedPoint.getLat() + "&lon=" + clickedPoint.getLon();
                    URL url = new URL(urlString);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("User-Agent", "Mozilla/5.0");

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();

                    JSONObject json = new JSONObject(response.toString());
                    countryName = json.getString("display_name");
                    // Extract the country name from the display name
                    int index = countryName.lastIndexOf(",");
                    if (index != -1) {
                        countryName = countryName.substring(index + 2);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                // Print the name of the country
                System.out.println("Selected country: " + countryName);
                modeleInput.setText(countryName);

            }
        });

        // Create a JPanel and add the mapViewer to it
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(mapViewer, BorderLayout.CENTER);

        // Set the JPanel as the content of the SwingNode
        swingNode.setContent(panel);
           
           
           
           
        
        
        
           
            validerBtn.setOnAction(i  -> {
                
                
                String etatTexte = etatInput.getText().toLowerCase();
            if (etatTexte.contains("shit") || etatTexte.contains("fuck") || etatTexte.contains("test")) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("L etat contient des mots interdits!");
                alert.show();
            }

            else if  ( "".equals(nomInput.getText()) || "".equals (mailInput.getText()) || !mailInput.getText().contains("@")
            
                || "".equals (modeleInput.getText()) || "".equals (ageInput.getText()) || !isNumeric(ageInput.getText()) || "".equals(ageInput.getText()) )  {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Information Dialog");

                alert.setHeaderText(null);

                alert.setContentText("Veuillez remplir tous les champs correctement!");

                alert.show();

            } else {

                try {

                    DonVeloCrud dvc = new DonVeloCrud();
                    int age = Integer.parseInt(ageInput.getText());
                    
                   DonVelo in = new DonVelo(age , modeleInput.getText(), etatInput.getText() , mailInput.getText(), nomInput.getText() );
                    //verification de l'unicite par le nom de la demande le budget la description et la date limite 
                    String ageString = String.valueOf(in.getAge());
                    String sql = "SELECT * FROM don_velo WHERE nom = ? AND email = ?  AND etat = ? AND  age = ?";
                    PreparedStatement stmt;
                    stmt = cnx2.prepareStatement(sql);
                    stmt.setString(1, in.getNom());
                    stmt.setString(2, in.getEmail());
                    //stmt.setString(3, in.getModele());
                    stmt.setString(3, in.getEtat());
                    stmt.setString(4, ageString);
                    

                    
                    ResultSet rs = stmt.executeQuery();
                    if (rs.next()) {

                        Alert alert = new Alert(Alert.AlertType.INFORMATION);

                        alert.setTitle("Information Dialog");

                        alert.setHeaderText(null);

                        alert.setContentText("Don existe deja!");

                        alert.show();
                        return; 
                    } else {

                        dvc.ajout(in);
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
    private void validerBtnOnAction(ActionEvent event) {
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        
        
           Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) retourBtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
