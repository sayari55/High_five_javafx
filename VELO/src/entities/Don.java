/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Ines
 */
public class Don {
    
    private int id;
    private String nom, email, type, motif;

    public Don() {
    }

    public Don(int id, String nom, String email, String type, String motif) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.type = type;
        this.motif = motif;
    }

    public Don(int id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public Don(String nom, String email, String type, String motif) {
        this.nom = nom;
        this.email = email;
        this.type = type;
        this.motif = motif;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    @Override
    public String toString() {
        return "Don{" + "id=" + id + ", nom=" + nom + ", email=" + email + ", type=" + type + ", motif=" + motif + '}';
    }

    
}





