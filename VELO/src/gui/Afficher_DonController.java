

package gui;

import entities.Don;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import services.DonCrud;

/**
 * FXML Controller class
 *
 * @author MSI
 */
public class Afficher_DonController implements Initializable {

    @FXML
    private TableView<Don> invitetv;
    @FXML
    private TableColumn<Don, Integer> id;
    @FXML
    private TableColumn<Don, String> nom;
    @FXML
    private TableColumn<Don, String> email;
    @FXML
    private TableColumn<Don, String> type;
    @FXML
    private TableColumn<Don, String> motif;
    @FXML
    private TableColumn<Don, Button> delete;
    @FXML
    private TableColumn<Don, Button> modifier;
   
   DonCrud ps = new DonCrud();
    @FXML
    private Label userNamePlaceholder;
    @FXML
    private Button DonparArgent;
    @FXML
    private Button DonparVelo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    List<Don> Don;
            Don = ps.afficher();
            ObservableList<Don> olp = FXCollections.observableArrayList(Don);
            invitetv.setItems(olp);
            id.setCellValueFactory(new PropertyValueFactory("id"));
            nom.setCellValueFactory(new PropertyValueFactory("nom"));
            email.setCellValueFactory(new PropertyValueFactory("email"));
            type.setCellValueFactory(new PropertyValueFactory("type"));
            motif.setCellValueFactory(new PropertyValueFactory("motif"));
   
            this.delete();
            this.modifier();
            
           
            
    }    
    
    
    
    
    private void delete() {

        delete.setCellFactory((param) -> {
            return new TableCell() {
                @Override
                protected void updateItem(Object item, boolean empty) {
                    setGraphic(null);
                    if (!empty) {
                        Button b = new Button("delete");
                        b.setOnAction((event) -> {
                            if (showConfirmationDialog("Confirmation de suppression", "Voulez-vous vraiment supprimer cet élément ?")) {
                            if (ps.supprimer(invitetv.getItems().get(getIndex()).getId())) {
                                invitetv.getItems().remove(getIndex());
                                invitetv.refresh();
                            }
                            }

                        });
                        setGraphic(b);

                    }
                }
            };

        });
    }
        
        
        private boolean showConfirmationDialog(String title, String message) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(null);
    alert.setContentText(message);

    Optional<ButtonType> result = alert.showAndWait();
    return result.isPresent() && result.get() == ButtonType.OK;
}

         
       
         
         private void modifier() {
    modifier.setCellFactory((param) -> {
        return new TableCell() {
            protected void updateItem(Object item, boolean empty) {
                setGraphic(null);
                if (!empty) {
                    Button b = new Button("modifier");
                    b.setOnAction((event) -> {
                        if (showConfirmationDialog("Confirmation de modification", "Voulez-vous vraiment modifier cet élément ?")) {
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("Modifier_Don.fxml"));
                                Parent root = loader.load();
                                gui.Modifier_DonController modifier = loader.getController();
                                Don c = invitetv.getItems().get(getIndex());

                                modifier.setDon(c);
                                Scene scene = b.getScene();
                                scene.setRoot(root);
                            } catch (IOException ex) {
                                Logger.getLogger(Afficher_DonController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    setGraphic(b);
                }
            }
        };
    });
}

       @FXML
    private void btndonArgent(ActionEvent event) throws IOException {
        
        
           Parent root = FXMLLoader.load(getClass().getResource("Afficher_Don.fxml"));
            Stage stage = (Stage) DonparArgent.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    @FXML
    private void btnDonVelo(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("AfficherDon_Velo.fxml"));
            Stage stage = (Stage) DonparVelo.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }
    }
         
    
    
    
    
    
    
