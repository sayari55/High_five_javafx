/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package javafxpi;

import ecovelojava.entities.Avis;
import ecovelojava.entities.User;
import ecovelojava.services.AvisService;
import ecovelojava.services.UserService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Random;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import utils.JavaMailUtil;

/**
 *
 * @author khaliljebali
 */
public class JavaFXPi extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AdminReviewPage.fxml"));
        primaryStage.setTitle("Eco Velo");
        primaryStage.setScene(new Scene(root , 1000 , 500)); //520 400
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, Exception {
        
         UserService us = new UserService();
            AvisService as = new AvisService();
        User u = new User(31,52653322 , 52 , "test" , "new@new.com" , "testtest" ,"23 rue de rome" , 6666  );
//            User u = new User(0000 , "new@new.com");
Avis a = new Avis(57,3, "test");
//            System.out.println(as.afficher(a));
//            as.recuperer();
//System.out.println(as.recuperer());
//us.modifierByEmail(u);

//us.access(u);


//System.out.println(us.afficher(u));

//System.out.println(us.afficherByEmail(u));

//    Random rand = new Random();
//    String test = String.valueOf(rand);

//    System.out.println(us.afficher(u));
//        String mailText = " test test test test ";
//        String subject="testing the function";
//
//            JavaMailUtil.sendMail("khalil.jebali.kj@hotmail.com", subject ,mailText);
            

//        us.modifier(u);
//        System.out.println(us.recuperer());
        launch(args);
    }
    
}
