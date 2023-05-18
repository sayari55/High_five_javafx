package gui;

import entities.Don;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
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
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import services.DonCrud;

public class AfficherevController implements Initializable {

    private HBox scrollAfficherAvis;
    private List<Don> Don;
    DonCrud sa = new DonCrud();
    @FXML
    private Label factLabel;
    @FXML
    private Button ParArgentbtn;
    @FXML
    private Button Parvelobtn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Don = sa.afficher();
            for (int i = 0; i < Don.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("cadrev.fxml"));
                HBox cardBox = fxmlLoader.load();
                CadrevController cadreController = fxmlLoader.getController();
                cadreController.setData(Don.get(i));
                scrollAfficherAvis.getChildren().add(cardBox);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfficherevController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfficherevController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
    @FXML
    private void argent(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AjouterDon.fxml"));
            Stage stage = (Stage) ParArgentbtn.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

}

