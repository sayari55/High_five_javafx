/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecovelojava.entities;

/**
 *
 * @author khaliljebali
 */
public class Avis {
    
    private int id , user_id , note , etat;
    private String description;

    public Avis() {
    }

//    public Avis(int id) {
//        this.id = id;
//    }

    public Avis(int user_id) {
        this.user_id = user_id;
    }

    public Avis(int id, int user_id) {
        this.id = id;
        this.user_id = user_id;
    }
    
    

    
    
    public Avis(int user_id, int note, String description) {
        this.user_id = user_id;
        this.note = note;
        this.description = description;
    }

    
    
    
    public Avis(int id, int user_id, int note, int etat, String description) {
        this.id = id;
        this.user_id = user_id;
        this.note = note;
        this.etat = etat;
        this.description = description;
    }

    public Avis(int id, int user_id, int note, String description) {
        this.id = id;
        this.user_id = user_id;
        this.note = note;
        this.description = description;
    }
    
    
    

    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", userId=" + user_id + ", note=" + note + ", etat=" + etat + ", description=" + description + '}';
    }
    
    
    
    
}
