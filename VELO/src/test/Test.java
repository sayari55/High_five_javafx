/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;





/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static jdk.internal.vm.ThreadContainers.root;


/**
 *
 * @author MSI
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ASUS
 */
public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
         // Code pour initialiser votre interface
      
         // Charger le fichier FXML
       
        //  Parent root = FXMLLoader.load(getClass().getResource("/gui/AjouterDon.fxml"));
             //    Parent root = FXMLLoader.load(getClass().getResource("/gui/Afficher_Don.fxml"));
       //   Parent root = FXMLLoader.load(getClass().getResource("/gui/AfficherDon_Velo.fxml"));
             //      Parent root = FXMLLoader.load(getClass().getResource("/gui/AjouterDon_velo.fxml"));
      //Parent root = FXMLLoader.load(getClass().getResource("/gui/ModifierDon_Velo.fxml"));
      
            // Parent root = FXMLLoader.load(getClass().getResource("/gui/StatisticsDon.fxml"));
            //  Parent root = FXMLLoader.load(getClass().getResource("/gui/RandomVeloFacts.fxml"));
                     //       Parent root = FXMLLoader.load(getClass().getResource("/gui/sample.fxml"));
              //   Parent root = FXMLLoader.load(getClass().getResource("/gui/Home.fxml"));

              
              
              

          //  Parent root = FXMLLoader.load(getClass().getResource("/gui/cadrev.fxml"));
        //  Parent root = FXMLLoader.load(getClass().getResource("/gui/AfficherevController.fxml"));
          

          
          
          

           
           

        // Créer une scène à partir de la racine
        Scene scene = new Scene(root);

        // Définir les propriétés de la scène et l'afficher
        primaryStage.setTitle("Mon application JavaFX");
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        
        
        
        
        launch(args);

       
    }

   
    
}












