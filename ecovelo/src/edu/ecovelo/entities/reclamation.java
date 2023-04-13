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

   
    private int id_reclamation ; 
    private String nom;

   
    private String email ; 
     private String sujet ;
    private String description ; 
    
   
public Date date_reclamation ; 
 private String etat ; 
 private int id_util;
 public reclamation(int id_reclamation, String nom, String email, String sujet, String description, Date date_reclamation, String etat) {
        this.id_reclamation = id_reclamation;
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.etat = etat;
    }
    public int getId_util() {
        return id_util;
    }

    public void setId_util(int id_util) {
        this.id_util = id_util;
    }

    public int getId_reclamation() {
        return id_reclamation;
    }

    public void setId_reclamation(int id_reclamation) {
        this.id_reclamation = id_reclamation;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public reclamation(int id_reclamation, String nom, String email, String sujet, String description, Date date_reclamation, String etat, int id_util) {
        this.id_reclamation = id_reclamation;
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.etat = etat;
        this.id_util = id_util;
    }

    public reclamation(String nom, String email, String sujet, String description, Date date_reclamation, String etat, int id_util) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.etat = etat;
        this.id_util = id_util;
    }

    public reclamation(String nom, String email, String sujet, String description, String etat, int id_util) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
        this.id_util = id_util;
    }

    public reclamation(String nom, String email, String sujet, String description, String etat) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.description = description;
        this.etat = etat;
    }

    public reclamation(String nom, String email, String sujet, String description, Date date_reclamation, String etat) {
        this.nom = nom;
        this.email = email;
        this.sujet = sujet;
        this.description = description;
        this.date_reclamation = date_reclamation;
        this.etat = etat;
    }



  

   

   
    

    
    
}

