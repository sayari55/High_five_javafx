/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author Ines
 */
public class DonVelo {
    
    int id , age;
    String modele , etat , motif , email , nom;

    public DonVelo() {
    }

   

    public DonVelo(int id, int age, String modele, String etat, String motif, String email, String nom) {
        this.id = id;
        this.age = age;
        this.modele = modele;
        this.etat = etat;
        this.motif = motif;
        this.email = email;
        this.nom = nom;
    }

    public DonVelo(int age, String modele, String etat, String email, String nom) {
        this.age = age;
        this.modele = modele;
        this.etat = etat;
        
        this.email = email;
        this.nom = nom;
    }

    public DonVelo(int id, int age, String modele, String etat, String email, String nom) {
        this.id = id;
        this.age = age;
        this.modele = modele;
        this.etat = etat;
        this.email = email;
        this.nom = nom;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "DonVelo{" + "id=" + id + ", age=" + age + ", modele=" + modele + ", etat=" + etat + ", motif=" + motif + ", email=" + email + ", nom=" + nom + '}';
    }

  
}