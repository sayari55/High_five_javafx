package gui;

import entities.Categorie;
import entities.Velo;
import services.CategorieService;
import services.VeloService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class CategoriesController implements Initializable {

    @FXML
    private Button ajBtn;

    @FXML
    private TextField description;

    @FXML
    private TextField description1;

    @FXML
    private TableColumn<?, ?> descriptionColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TextField modele;

    @FXML
    private TextField modele1;

    @FXML
    private TableColumn<?, ?> modeleColumn;

    @FXML
    private TextField nom;

    @FXML
    private TextField nom1;

    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableView<Categorie> tableViewCategories;

    @FXML
    void ajouter(ActionEvent event) {
        String nomCategorie = nom.getText().trim();
        String descriptionCategorie = description.getText().trim();
        String modeleCategorie = modele.getText().trim();

        // Vérifie si les champs obligatoires sont remplis
        if (nomCategorie.isEmpty() || modeleCategorie.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return;
        }

        Categorie categorie = new Categorie(nomCategorie, descriptionCategorie, modeleCategorie);
        CategorieService categorieService = new CategorieService();
        categorieService.ajouter(categorie);
        List<Categorie> velos = categorieService.afficher();
        tableViewCategories.setItems(FXCollections.observableList(velos));


        // Affiche une alerte de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie ajoutée avec succès !");
        alert.showAndWait();

        // Actualise la table des catégories
        List<Categorie> categories = categorieService.afficher();
        tableViewCategories.setItems(FXCollections.observableList(categories));
    }


    @FXML
    void modifier(ActionEvent event) {
        // Récupère la catégorie sélectionnée dans la table
        Categorie categorieSelectionnee = tableViewCategories.getSelectionModel().getSelectedItem();

        // Vérifie si une catégorie est sélectionnée
        if (categorieSelectionnee == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une catégorie à modifier.");
            alert.showAndWait();
            return;
        }

        // Récupère les nouvelles valeurs des champs de texte
        String nomCategorie = nom1.getText().trim();
        String descriptionCategorie = description1.getText().trim();
        String modeleCategorie = modele1.getText().trim();

        // Vérifie si les champs obligatoires sont remplis
        if (nomCategorie.isEmpty() || modeleCategorie.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erreur");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez remplir tous les champs obligatoires.");
            alert.showAndWait();
            return;
        }

        // Crée un objet Categorie avec les nouvelles valeurs
        Categorie nouvelleCategorie = new Categorie(categorieSelectionnee.getId(), nomCategorie, descriptionCategorie, modeleCategorie);

        // Appelle la méthode modifier de CategorieService pour mettre à jour la catégorie dans la base de données
        CategorieService categorieService = new CategorieService();
        categorieService.modifier(nouvelleCategorie);
        List<Categorie> velos = categorieService.afficher();
        tableViewCategories.setItems(FXCollections.observableList(velos));


        // Affiche une alerte de succès
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Succès");
        alert.setHeaderText(null);
        alert.setContentText("Catégorie modifiée avec succès !");
        alert.showAndWait();

        // Actualise la table des catégories
        List<Categorie> categories = categorieService.afficher();
        tableViewCategories.setItems(FXCollections.observableList(categories));
    }

    @FXML
    void selection(MouseEvent event) {
        Categorie categorieSelectionnee = tableViewCategories.getSelectionModel().getSelectedItem();
        if (categorieSelectionnee != null) {
            //id.setText(String.valueOf(categorieSelectionnee.getId()));
            nom1.setText(categorieSelectionnee.getNom());
            description1.setText(categorieSelectionnee.getDescription());
            modele1.setText(categorieSelectionnee.getModele());
        }
    }


    @FXML
    void supprimer(ActionEvent event) {
        Categorie categorieSelectionnee = tableViewCategories.getSelectionModel().getSelectedItem();
        if (categorieSelectionnee != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation de suppression");
            alert.setHeaderText(null);
            alert.setContentText("Êtes-vous sûr de vouloir supprimer cette catégorie ?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CategorieService categorieService = new CategorieService();
                categorieService.supprimer(categorieSelectionnee);
                List<Categorie> categories = categorieService.afficher();
                tableViewCategories.setItems(FXCollections.observableList(categories));
                System.out.println("Catégorie supprimée avec succès !");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aucune catégorie sélectionnée");
            alert.setHeaderText(null);
            alert.setContentText("Veuillez sélectionner une catégorie à supprimer.");
            alert.showAndWait();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Create an instance of the VeloService class
        CategorieService categorieService = new CategorieService();
        List<Categorie> velos = categorieService.afficher();
        tableViewCategories.setItems(FXCollections.observableList(velos));

        // Set the cell value factories for the columns
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        modeleColumn.setCellValueFactory(new PropertyValueFactory<>("modele"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        nomColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
    }
}
