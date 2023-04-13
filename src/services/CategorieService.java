package services;

import entities.Categorie;
import utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService implements IService<Categorie> {

    private Connection cnx;

    // Constructeur
    public CategorieService() {
        cnx = DataSource.getInstance().getCnx();
    }

    @Override
    public void ajouter(Categorie c) {
        try {
            String requete = "INSERT INTO categorie (id, nom, description, modele) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.setString(2, c.getNom());
            pst.setString(3, c.getDescription());
            pst.setString(4, c.getModele());
            pst.executeUpdate();
            System.out.println("Catégorie ajoutée avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Categorie c) {
        try {
            String requete = "UPDATE categorie SET nom=?, description=?, modele=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setString(3, c.getModele());
            pst.setInt(4, c.getId());
            pst.executeUpdate();
            System.out.println("Catégorie modifiée avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Categorie c) {
        try {
            String requete = "DELETE FROM categorie WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, c.getId());
            pst.executeUpdate();
            System.out.println("Catégorie supprimée avec succès !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public List<Categorie> afficher() {
        List<Categorie> listCategories = new ArrayList<>();
        try {
            String requete = "SELECT * FROM categorie";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            while (rs.next()) {
                Categorie c = new Categorie(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("modele")
                );
                listCategories.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listCategories;
    }

    public Categorie chercher(int id) {
        Categorie c = null;
        try {
            String requete = "SELECT * FROM categorie WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                c = new Categorie(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("description"),
                        rs.getString("modele")
                );
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }
}
