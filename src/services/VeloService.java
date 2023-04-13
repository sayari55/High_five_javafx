package services;

import entities.Velo;
import utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeloService {

    private Connection cnx;

    // Constructeur
    public VeloService() {
        cnx = DataSource.getInstance().getCnx();
    }

    public boolean ajouter(Velo v) {
    boolean isSuccess = false;
    try {
        String requete = "INSERT INTO velo (id, couleur, etat, id_station, categorie_id, image) VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, v.getId());
        pst.setString(2, v.getCouleur());
        pst.setString(3, v.getEtat());
        pst.setInt(4, v.getIdStation());
        pst.setInt(5, v.getCategorieId());
        pst.setString(6, v.getImage());
        int rowsInserted = pst.executeUpdate();
        if (rowsInserted > 0) {
            isSuccess = true;
            System.out.println("Velo ajouté avec succès !");
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
    return isSuccess;
}


    public boolean modifier(Velo v) {
try {
String requete = "UPDATE velo SET couleur=?, etat=?, id_station=?, categorie_id=?, image=? WHERE id=?";
PreparedStatement pst = cnx.prepareStatement(requete);
pst.setString(1, v.getCouleur());
pst.setString(2, v.getEtat());
pst.setInt(3, v.getIdStation());
pst.setInt(4, v.getCategorieId());
pst.setString(5, v.getImage());
pst.setInt(6, v.getId());
int resultat = pst.executeUpdate();
System.out.println("Velo modifié avec succès !");
return (resultat > 0); // Si au moins une ligne a été modifiée, on retourne true
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false; // En cas d'erreur, on retourne false
    }
}


    public boolean supprimer(Velo v) {
    try {
        String requete = "DELETE FROM velo WHERE id=?";
        PreparedStatement pst = cnx.prepareStatement(requete);
        pst.setInt(1, v.getId());
        int result = pst.executeUpdate();
        if (result == 1) {
            System.out.println("Velo supprimé avec succès !");
            return true;
        } else {
            System.out.println("Aucun vélo n'a été supprimé !");
            return false;
        }
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
        return false;
    }
}

    public List<Velo> afficher() {
        List<Velo> listVelos = new ArrayList<>();
        try {
            String requete = "SELECT * FROM velo";
            Statement stm = cnx.createStatement();
            ResultSet rs = stm.executeQuery(requete);
            while (rs.next()) {
                Velo v = new Velo(
                        rs.getInt("id"),
                        rs.getString("couleur"),
                        rs.getString("etat"),
                        rs.getInt("id_station"),
                        rs.getInt("categorie_id"),
                        rs.getString("image")
                );
                listVelos.add(v);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return listVelos;
    }

    public Velo chercher(int id) {
        Velo v = null;
        try {
            String requete = "SELECT * FROM velo WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(requete);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                v = new Velo(
                        rs.getInt("id"),
                        rs.getString("couleur"),
                        rs.getString("etat"),
                        rs.getInt("id_station"),
                        rs.getInt("categorie_id"),
                        rs.getString("image")
                );
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return v;
    }
}
