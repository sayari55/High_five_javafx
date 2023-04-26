/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.entities;

import java.util.Date;

/**
 *
 * @author Mohamed Sayari
 */
public class reclamation {

   
    private int id ; 
    private String nom;
    private String description ; 
    private String email ; 
    private String sujet ;
    
    private String etat ;

    public reclamation(String nom, String description, String email, String sujet, String etat) {
        this.nom = nom;
        this.description = description;
        this.email = email;
        this.sujet = sujet;
        this.etat = etat;
    }

    public reclamation(int id, String nom, String description, String email, String sujet, String etat) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.email = email;
        this.sujet = sujet;
        this.etat = etat;
    }

   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
    
}

