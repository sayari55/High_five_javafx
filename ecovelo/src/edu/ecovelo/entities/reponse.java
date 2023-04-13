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
public class reponse {
    
    private int id_reponse ; 
    private int id_reclamation_id;
    private Date date_reponse ; 
     private String sujet_reponse ;
    private String motif ;
    private int avis;

    public int getId_reponse() {
        return id_reponse;
    }

    public reponse(int id_reclamation_id, String sujet_reponse, String motif) {
        this.id_reclamation_id = id_reclamation_id;
        this.sujet_reponse = sujet_reponse;
        this.motif = motif;
    }

    public reponse(int id_reponse, int id_reclamation_id, Date date_reponse, String sujet_reponse, String motif) {
        this.id_reponse = id_reponse;
        this.id_reclamation_id = id_reclamation_id;
        this.date_reponse = date_reponse;
        this.sujet_reponse = sujet_reponse;
        this.motif = motif;
    }

    public reponse(int id_reclamation_id, Date date_reponse, String sujet_reponse, String motif) {
        this.id_reclamation_id = id_reclamation_id;
        this.date_reponse = date_reponse;
        this.sujet_reponse = sujet_reponse;
        this.motif = motif;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public int getId_reclamation_id() {
        return id_reclamation_id;
    }

    public void setId_reclamation_id(int id_reclamation_id) {
        this.id_reclamation_id = id_reclamation_id;
    }

    public Date getDate_reponse() {
        return date_reponse;
    }

    public void setDate_reponse(Date date_reponse) {
        this.date_reponse = date_reponse;
    }

    public String getSujet_reponse() {
        return sujet_reponse;
    }

    public void setSujet_reponse(String sujet_reponse) {
        this.sujet_reponse = sujet_reponse;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public int getAvis() {
        return avis;
    }

    public void setAvis(int avis) {
        this.avis = avis;
    }
}
