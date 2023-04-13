package entities;

public class Categorie {
    private int id;
    private String nom;
    private String description;
    private String modele;

    public Categorie(int id, String nom, String description, String modele) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.modele = modele;
    }

    public Categorie(String nom, String description, String modele) {
        this.nom = nom;
        this.description = description;
        this.modele = modele;
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

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    @Override
    public String toString() {
        return  id + ": =" + nom ;
    }

    
    
}

