/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import entities.Station;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import utils.MyDB;


/**
 *
 * @author khali
 */
public class StationService implements IService<Station> {
    Connection cnx;

    public StationService() {
        cnx = MyDB.getInstance().getCnx();
    }

    @Override
    public void ajouter(Station s) throws SQLException {
        PreparedStatement statement = null;
        statement= cnx.prepareStatement( "insert into station(capacite, localisation) VALUES" + "(?,?)");
        statement.setInt(1,s.getCapacite());
        statement.setString(2,s.getLocalisation());
        

        
        statement.executeUpdate();
        System.out.println("La station a été ajoutée avec succès !");
    }

    @Override
    public boolean supprimer(Station s) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("DELETE FROM station WHERE id = ?");
            req.setInt(1, s.getId());
            req.executeUpdate();
            ok = true;
            System.out.println("La station a été supprimée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la suppression de la station : " + ex.getMessage());
        }
        return ok;
    }

    @Override
    public void modifier(Station s) throws SQLException {
        String query = "UPDATE station SET capacite=?, localisation=? WHERE id = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, s.getCapacite());
            ste.setString(2, s.getLocalisation());
            ste.setInt(3, s.getId());
            ste.executeUpdate();
            System.out.println("La station a été modifiée avec succès !");
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la modification de la station : " + ex.getMessage());
        }
    }

    @Override
    public List<Station> afficher() throws SQLException {
        List<Station> stations = new ArrayList<>();
        String req = "SELECT * FROM station";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Station s = new Station();
            s.setId(rs.getInt("id"));
            s.setLocalisation(rs.getString("localisation"));

            s.setCapacite(rs.getInt("capacite"));
            stations.add(s);
        }
        return stations;
    }
}
