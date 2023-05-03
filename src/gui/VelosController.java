/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.google.zxing.qrcode.QRCodeWriter;
import entities.Categorie;
import entities.Station;
import entities.Velo;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javax.imageio.ImageIO;
import services.CategorieService;
import services.StationService;
import services.VeloService;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class VelosController implements Initializable {

    @FXML
    private ColorPicker couleur;
    @FXML
    private Button upload;
    @FXML
    private ComboBox<Categorie> categorie;
    @FXML
    private ComboBox<Station> station;
    @FXML
    private Button ajBtn;
    @FXML
    private ColorPicker couleur1;
    @FXML
    private TextField etat1;
    @FXML
    private Button upload1;
    @FXML
    private ComboBox<Categorie> categorie1;
    @FXML
    private Button ajBtn1;
    @FXML
    private TableView<Velo> tableViewVelos;
    @FXML
    private TableColumn<?, ?> idField;
    @FXML
    private TableColumn<?, ?> etatField;
    @FXML
    private TableColumn<?, ?> categorieField;
    @FXML
    private TableColumn<?, ?> stationField;
    @FXML
    private TableColumn<?, ?> couleurField;
    
    String imagePath;
    @FXML
    private TextField etat;
    @FXML
    private ComboBox<Station> station1;
    @FXML
    private ImageView image;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CategorieService categorieService = new CategorieService();
        VeloService veloService = new VeloService();
        List<Categorie> categories = categorieService.afficher();
        categorie.getItems().addAll(categories);
        List<Station> stations = null;
        StationService stationService = new StationService();
        try {
            stations = stationService.afficher();
        } catch (SQLException ex) {
            Logger.getLogger(VelosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        categorie.getItems().addAll(categories);
        station.getItems().addAll(stations);
        categorie1.getItems().addAll(categories);
        station1.getItems().addAll(stations);
        
        // Obtenez les données des vélos à afficher
List<Velo> listVelos = veloService.afficher();

// Convertir la liste en ObservableList pour l'afficher dans la TableView
ObservableList<Velo> observableListVelos = FXCollections.observableArrayList(listVelos);

// Associer les données avec les colonnes de la TableView
idField.setCellValueFactory(new PropertyValueFactory<>("id"));
etatField.setCellValueFactory(new PropertyValueFactory<>("etat"));
categorieField.setCellValueFactory(new PropertyValueFactory<>("categorieId"));
stationField.setCellValueFactory(new PropertyValueFactory<>("idStation"));
couleurField.setCellValueFactory(new PropertyValueFactory<>("couleur"));


// Afficher les données dans la TableView
tableViewVelos.setItems(observableListVelos);

tableViewVelos.setOnMouseClicked(event -> {
    if (event.getClickCount() == 2) {
        Velo selectedVelo = tableViewVelos.getSelectionModel().getSelectedItem();
        ImageView qrCodeImageView = genererQrCode(selectedVelo);
        Stage qrCodeStage = new Stage();
        qrCodeStage.setTitle("QR code for " + selectedVelo.getEtat());
        qrCodeStage.setScene(new Scene(new StackPane(qrCodeImageView)));
        qrCodeStage.show();
    }
});


        
    } 
    
    
    
    ImageView genererQrCode (Velo velo){
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "Velo: "+velo.getId()+ " , "+ velo.getIdStation()+ ", "+velo.getEtat()+ " ,"+velo.getCouleur();
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
           // Logger.getLogger(JavaFX_QRCodeWriter.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getMessage());
        }
        
        ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        return qrView; 
    }

    @FXML
    private void ajouter(ActionEvent event) {
        String couleurString = couleur.getValue().toString();
        String etatString = etat.getText();
        Categorie categorieSelectionnee = categorie.getValue();
        Station stationSelectionnee = station.getValue();
        String imagePath = this.imagePath; // Chemin absolu de l'image
        Velo nouveauVelo = new Velo(couleurString, etatString, stationSelectionnee.getId(), categorieSelectionnee.getId(), imagePath);
        VeloService veloService = new VeloService();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Ajout d'un nouveau vélo");
        alert.setHeaderText(null);
        
        if (veloService.ajouter(nouveauVelo)) {
            alert.setContentText("Le vélo a été ajouté avec succès !");
            alert.showAndWait();
            List<Velo> listVelos = veloService.afficher();
            ObservableList<Velo> observableListVelos = FXCollections.observableArrayList(listVelos);
            tableViewVelos.setItems(observableListVelos);

        } else {
            alert.setAlertType(AlertType.ERROR);
            alert.setContentText("Erreur lors de l'ajout du vélo !");
            alert.showAndWait();
        }
    }
    

    @FXML
    private void modifier(ActionEvent event) {Velo veloSelectionne = tableViewVelos.getSelectionModel().getSelectedItem();
if (veloSelectionne == null) {
// Si aucun vélo n'a été sélectionné dans la table, on affiche un message d'erreur
Alert alert = new Alert(AlertType.WARNING);
alert.setTitle("Modification d'un vélo");
alert.setHeaderText(null);
alert.setContentText("Veuillez sélectionner un vélo dans la table !");
alert.showAndWait();
return;
}
    String couleurString = couleur1.getValue().toString();
    String etatString = etat1.getText();
    Categorie categorieSelectionnee = categorie1.getValue();
    Station stationSelectionnee = station1.getValue();
    String imagePath = this.imagePath; // Chemin absolu de l'image
    Velo veloModifie = new Velo(couleurString, etatString, stationSelectionnee.getId(), categorieSelectionnee.getId(), imagePath);
    veloModifie.setId(veloSelectionne.getId()); // On récupère l'id du vélo sélectionné et on l'assigne au nouveau vélo
    VeloService veloService = new VeloService();
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Modification d'un vélo");
    alert.setHeaderText(null);
    
    if (veloService.modifier(veloModifie)) {
        alert.setContentText("Le vélo a été modifié avec succès !");
        alert.showAndWait();
        List<Velo> listVelos = veloService.afficher();
        ObservableList<Velo> observableListVelos = FXCollections.observableArrayList(listVelos);
        tableViewVelos.setItems(observableListVelos);

    } else {
        alert.setAlertType(AlertType.ERROR);
        alert.setContentText("Erreur lors de la modification du vélo !");
        alert.showAndWait();
    }


        
    }

    @FXML
private void selection(MouseEvent event) {
    Velo veloSelectionne = tableViewVelos.getSelectionModel().getSelectedItem();
    if (veloSelectionne != null) {
        // Affichage de l'image
        String imagePath = veloSelectionne.getImage();
        if (imagePath != null && !imagePath.isEmpty()) {
            File file = new File(imagePath);
            if (file.exists()) {
                Image veloImage = new Image(file.toURI().toString());
                image.setImage(veloImage);
            } else {
                System.out.println("Le fichier " + imagePath + " n'existe pas.");
            }
        } else {
            image.setImage(null); // Effacer l'image précédente
        }

        // Mise à jour des autres champs
        etat.setText(veloSelectionne.getEtat());
    }
}


@FXML
private void supprimer(ActionEvent event) {
    Velo veloSelectionne = tableViewVelos.getSelectionModel().getSelectedItem();
    if (veloSelectionne == null) {
        // Si aucun vélo n'a été sélectionné dans la table, on affiche un message d'erreur
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Suppression d'un vélo");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un vélo dans la table !");
        alert.showAndWait();
        return;
    }

    VeloService veloService = new VeloService();
    Alert alert = new Alert(AlertType.CONFIRMATION);
    alert.setTitle("Suppression d'un vélo");
    alert.setHeaderText(null);
    alert.setContentText("Êtes-vous sûr de vouloir supprimer le vélo sélectionné ?");

    Optional<ButtonType> result = alert.showAndWait();
    if (result.isPresent() && result.get() == ButtonType.OK) {
        if (veloService.supprimer(veloSelectionne)) {
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Suppression d'un vélo");
            alert.setHeaderText(null);
            alert.setContentText("Le vélo a été supprimé avec succès !");
            alert.showAndWait();
            List<Velo> listVelos = veloService.afficher();
            ObservableList<Velo> observableListVelos = FXCollections.observableArrayList(listVelos);
            tableViewVelos.setItems(observableListVelos);
        } else {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Suppression d'un vélo");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la suppression du vélo !");
            alert.showAndWait();
        }
    }
}

   

    @FXML
    private void uploadModif(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        String absolutePath = selectedFile.getAbsolutePath();
        // Utilisez le chemin absolu ici pour effectuer l'upload
        imagePath = absolutePath;
        System.out.println("Chemin absolu: " + absolutePath);
    }
    }
    
    @FXML
   public void uploadAjout(ActionEvent event) {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setTitle("Choisir une image");
    fileChooser.getExtensionFilters().addAll(
        new FileChooser.ExtensionFilter("Images", "*.png", "*.jpg", "*.gif")
    );
    File selectedFile = fileChooser.showOpenDialog(null);
    if (selectedFile != null) {
        String absolutePath = selectedFile.getAbsolutePath();
        // Utilisez le chemin absolu ici pour effectuer l'upload
        imagePath = absolutePath;
        System.out.println("Chemin absolu: " + absolutePath);
    }
}





    
}
