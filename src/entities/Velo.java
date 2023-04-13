package entities;

import java.time.LocalDateTime;

public class Velo {

    private int id;
    private String couleur;
    private String etat;
    private String idCategory;
    private int idStation;
    private int categorieId;
    private String image;
    private LocalDateTime updatedAt;
    private Integer imageSize;

    public Velo(int id, String couleur, String etat, String idCategory, int idStation, int categorieId, String image, LocalDateTime updatedAt, Integer imageSize) {
        this.id = id;
        this.couleur = couleur;
        this.etat = etat;
        this.idCategory = idCategory;
        this.idStation = idStation;
        this.categorieId = categorieId;
        this.image = image;
        this.updatedAt = updatedAt;
        this.imageSize = imageSize;
    }

    public Velo(String couleur, String etat, String idCategory, int idStation, int categorieId, String image, LocalDateTime updatedAt, Integer imageSize) {
        this.couleur = couleur;
        this.etat = etat;
        this.idCategory = idCategory;
        this.idStation = idStation;
        this.categorieId = categorieId;
        this.image = image;
        this.updatedAt = updatedAt;
        this.imageSize = imageSize;
    }
    

    public Velo(int id, String couleur, String etat, int idStation, int categorieId, String image) {
        this.id = id;
        this.couleur = couleur;
        this.etat = etat;
        this.idStation = idStation;
        this.categorieId = categorieId;
        this.image = image;
    }

    public Velo( String couleur, String etat, int idStation, int categorieId, String image) {
        this.couleur = couleur;
        this.etat = etat;
        this.idStation = idStation;
        this.categorieId = categorieId;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public int getIdStation() {
        return idStation;
    }

    public void setIdStation(int idStation) {
        this.idStation = idStation;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getImageSize() {
        return imageSize;
    }

    public void setImageSize(Integer imageSize) {
        this.imageSize = imageSize;
    }
}
