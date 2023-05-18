/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import entities.Don;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.animation.ScaleTransition;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import services.DonCrud;

/**
 * FXML Controller class
 *
 * @author Ines
 */
public class StatisticsDonController implements Initializable {

    @FXML
    private PieChart StatsChart;
    @FXML
    private ImageView GoBackBtn;
    
    DonCrud rs = new DonCrud();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        displayStatistics();
    }    
    


    public void displayStatistics() {
        // Récupérer tous les circuits de la base de données
        List<Don> dons = rs.afficher();
        // Regrouper les circuits par nom et nombre de places disponibles
        Map<String, Long> DonParNomEtmotif = dons.stream().collect( Collectors.groupingBy(c -> c.getNom()+ " (" + c.getMotif()+ " TND)", Collectors.counting()));
        // conversion circuitParNomEtPlacesDispo map à une liste d'objets PieChart.Data
        List<PieChart.Data> pieChartData = DonParNomEtmotif.entrySet().stream()
            .map(entry -> new PieChart.Data(entry.getKey(), entry.getValue())).collect(Collectors.toList());
        // Définir les éléments de données dans le PieChart
        StatsChart.setData(FXCollections.observableArrayList(pieChartData));
        // Configurer l'animation pour les données piechart
        StatsChart.getData().forEach(data ->
                data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, event -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(200), data.getNode());
                    st.setToX(1.1);
                    st.setToY(1.1);
                    st.play();
                })
        );
        StatsChart.getData().forEach(data ->
                data.getNode().addEventHandler(MouseEvent.MOUSE_EXITED, event -> {
                    ScaleTransition st = new ScaleTransition(Duration.millis(200), data.getNode());
                    st.setToX(1.0);
                    st.setToY(1.0);
                    st.play();
                })
        );
        // Configurer l'interactivité pour les données piechart
        StatsChart.getData().forEach(data ->
                data.getNode().addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
                    // Obtenir la valeur actuelle de l'élément data
                  //  long currentValue = (long) data.getPieValue();
                    
                    // Afficher un message avec la valeur de data
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Statistique Don(s)");
                    alert.setHeaderText(data.getName().split("\\(")[0]);
                   //String[] data1=data.getName().spl;
                    alert.setContentText("Montant donner : " + data.getName().split("\\(")[1].replace(")", ""));
                    alert.showAndWait();
                })
        );
    }




    @FXML
    private void GoBk(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Gestion_Circuit.fxml"));
            Parent root = loader.load();

            // Set the root of the current scene to the new FXML file
            GoBackBtn.getScene().setRoot(root);
    }
    
}
