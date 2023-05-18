/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

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

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class RandomVeloFactsController implements Initializable {

    @FXML
    private Label factLabel;
    @FXML
    private Button generateButton;
    @FXML
    private Button Retour;
    
     private String[] facts = {
        "Le mot \"bicyclette\" a été inventé en France dans les années 1860",
        "La première bicyclette avec des pédales a été inventée en France en 1861.",
        "La célèbre course cycliste Tour de France a été créée en France en 1903.",

        "La bicyclette la plus chère jamais vendue était un vélo fabriqué à la main en 2014 par un artisan suisse. Il a été vendu aux enchères pour plus de 100 000 euros.",

        "Les premières bicyclettes étaient fabriquées en bois.",

        "En France, il est obligatoire de porter un casque de vélo pour les enfants de moins de 12 ans.",

        "Le record du monde de vitesse à vélo a été établi en France en 2019, avec une vitesse de 280 km/h sur une piste spécialement construite.",

        "La bicyclette a été un élément clé de l'émancipation des femmes à la fin du XIXe siècle, car elle leur a donné une plus grande liberté de mouvement et d'indépendance.",

        "La bicyclette est un moyen de transport écologique et peu coûteux, idéal pour les déplacements urbains de courte distance.",

        "Les premières courses de vélo ont eu lieu en France dans les années 1860, et depuis lors, le pays est devenu l'un des leaders mondiaux du cyclisme.",

     };
    @FXML
    private Label userNamePlaceholder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void generateFact(ActionEvent event) {
        
         int index = (int) (Math.random() * facts.length);
        String fact = facts[index];
        factLabel.setText(fact);
    }

    @FXML
    private void retourbtn(ActionEvent event) throws IOException {
        
           Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
            Stage stage = (Stage) Retour.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    
}
