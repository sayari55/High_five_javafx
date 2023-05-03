package gui;

import com.sun.javafx.charts.Legend;
import com.sun.javafx.charts.Legend.LegendItem;
import entities.Categorie;
import java.io.File;
import java.io.IOException;
import services.CategorieService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.font.PDType1Font;



import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;



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
    private TextField search;

    @FXML
    private TextField modele1;

    @FXML
    private TableColumn<?, ?> modeleColumn;

    @FXML
    private TextField nom;

    @FXML
    private TextField nom1;
    
        @FXML
    private Pane pane;


    @FXML
    private TableColumn<?, ?> nomColumn;

    @FXML
    private TableView<Categorie> tableViewCategories;
    
        @FXML
    private PieChart pie;


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
            List<Categorie> listCategories = categorieService.afficher();
            
            search.textProperty().addListener((observable, oldValue, newValue) -> {
    List<Categorie> categories = categorieService.search(newValue);
    tableViewCategories.setItems(FXCollections.observableList(categories));
});

            
            

// Create the legend
Legend legend = new Legend();
Random random = new Random();
for (Categorie c : listCategories) {
    int velosCount = categorieService.getVelosByCategorie(c).size();
    String legendText = c.getNom() + " (" + velosCount + ")";
    Color color = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256),0);
    LegendItem legendItem = new LegendItem(legendText, new Rectangle(10, 10, color));
    legend.getItems().add(legendItem);
}

// Create the pie chart
PieChart pie = new PieChart();
ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
for (Categorie c : listCategories) {
    int velosCount = categorieService.getVelosByCategorie(c).size();
    pieChartData.add(new PieChart.Data(c.getNom(), velosCount));
}
pie.setData(pieChartData);

// Add the pie chart and the legend to a pane
pane.getChildren().addAll(pie, legend);

// Add the pane to your scene
//Scene scene = new Scene(pane);
       

}
    
        @FXML
    void pdf(ActionEvent event) {
        

        try {
            // Create a PDF document
            PDDocument document = new PDDocument();
            PDFont font = PDType0Font.load(document, new File("C:\\Users\\Lenovo\\Documents\\NetBeansProjects\\JFX\\src\\gui\\OpenSans-Bold.ttf"));
            
// Create a new page
PDPage page = new PDPage();
document.addPage(page);

// Create a new table for the content
PDPageContentStream contentStream = null;
try {
    contentStream = new PDPageContentStream(document, page);
} catch (IOException ex) {
    Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
}
float tableWidth = page.getMediaBox().getWidth() - (72 * 2); // Subtracting margins
float yStartNewPage = page.getMediaBox().getHeight() - (72 * 2); // Subtracting margins
boolean drawContent = true;
float bottomMargin = 70;
float yPosition = yStartNewPage;
int rowsPerPage = 30;

//TableView<YourObject> tableView = new TableView<>(); // Replace with your TableView object
ObservableList<TableColumn<Categorie, ?>> columns = tableViewCategories.getColumns();
int numberOfColumns = columns.size();

// Calculate the column width
float[] columnWidths = new float[numberOfColumns];
for (int i = 0; i < numberOfColumns; i++) {
    TableColumn<Categorie, ?> column = columns.get(i);
    columnWidths[i] = (float) (column.getWidth() / tableWidth * 100);
}

String headers = "nom"+" # "+"description"+" # modele";
    contentStream.beginText();
    contentStream.setFont(font, 12);
    
        contentStream.newLineAtOffset((72 * 2) , yPosition);
        contentStream.showText(headers);
        contentStream.endText();

yPosition -= 20;

// Add table rows
for (Categorie item : tableViewCategories.getItems()) {
    // Check if we need to start a new page
    if (yPosition <= bottomMargin) {
        contentStream.close();
        page = new PDPage();
        document.addPage(page);
        contentStream = new PDPageContentStream(document, page);
        yPosition = yStartNewPage;
    }
    
    TableColumn <Categorie, ?> column1 = columns.get(1);
    TableColumn <Categorie, ?> column2 = columns.get(2);
    TableColumn <Categorie, ?> column3 = columns.get(3);
    
    String cellData = column1.getCellData(item).toString()+" # "+column2.getCellData(item).toString()+" # "+column3.getCellData(item).toString();
    contentStream.beginText();
    contentStream.setFont(font, 12);
    
        contentStream.newLineAtOffset((72 * 2) , yPosition);
        contentStream.showText(cellData);
        contentStream.endText();
    // Add row data
    

    yPosition -= 20;
}

// Close the content stream and save the document
contentStream.close();
document.save("table.pdf");
document.close();

        } catch (IOException ex) {
            Logger.getLogger(CategoriesController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    
    
}
