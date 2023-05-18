/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Don;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javax.imageio.ImageIO;
import utils.MyDB;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class CadrevController implements Initializable {

    @FXML
    private VBox box_avis;
    @FXML
    private Label nom;
    @FXML
    private ImageView image_user;
    @FXML
    private Label type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    private final String[] colors = {"#bc9f1e", "#bc9f1e", "#bc9f1e", "#bc9f1e"};

    public void setData(Don a) throws SQLException {
        Connection cnx = MyDB.getInstance().getCnx();
        String s = "select nom,type from Don where id= " + a.getId();
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(s);
        String nom = null, type = null, image = null;
        while (rs.next()) {
            nom = rs.getString("nom");
            type = rs.getString("type");
        }


        
       
        this.nom.setText(a.getNom());
        this.type.setText(a.getType());
        
        box_avis.setStyle("-fx-background-color: " + colors[(int) (Math.random() * colors.length)] + ";"
                + " -fx-background-radius: 15;"
                + "-fx-effect: dropShadown(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0,10) ;");
        
       
    }
}
