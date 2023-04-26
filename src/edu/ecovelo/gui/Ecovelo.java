package edu.ecovelo.gui;

import edu.ecovelo.entities.reclamation;
import edu.ecovelo.services.ReclamationService;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import edu.ecovelo.utils.MyConnection;

public class Ecovelo extends Application {

    @Override
    public void start(Stage primaryStage) {
        
    }

    public static void main(String[] args) {
        ReclamationService service = new ReclamationService();

       
        reclamation r = new reclamation("John Doe", "john.doe@example.com", "Problème avec un vélo", "Le vélo numéro 123456 ne fonctionne pas.", "Nouvelle");
        service.insert(r);
        if (r.getId() != 0) {
            System.out.println("Insertion réussie ! ID de la réclamation : " + r.getId());
        } else {
            System.out.println("Insertion échouée !");
        }

        
        System.out.println("Liste des réclamations :");
        for (reclamation rec : service.readAll()) {
            System.out.println("ID : " + rec.getId() + ", Nom : " + rec.getNom() + ", Email : " + rec.getEmail() + ", Sujet : " + rec.getSujet() + ", Description : " + rec.getDescription() + ", État : " + rec.getEtat());
        }

        
        r.setEtat("Résolu");
        service.updateRecrut(r);
        if (service.readAll().get(0).getEtat().equals("Résolu")) {
            System.out.println("Mise à jour réussie !");
        } else {
            System.out.println("Mise à jour échouée !");
        }

        
        service.delete(r.getId());
        if (service.readAll().isEmpty()) {
            System.out.println("Suppression réussie !");
        } else {
            System.out.println("Suppression échouée !");
        }
    }

}