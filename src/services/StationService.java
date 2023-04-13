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
import utils.DataSource;


/**
 *
 * @author khali
 */
public class StationService {
    Connection cnx;

    public StationService() {
        cnx = DataSource.getInstance().getCnx();
    }

    public void ajouter(Station s) throws SQLException {
        String req = "INSERT INTO station(capacite, localisation) VALUES ('" + s.getCapacite() + "'," + s.getLocalisation() + ")";

        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("La station a été ajoutée avec succès !");
    }

    public void supprimer(Station s)  {
        
    }

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

    public List<Station> afficher() throws SQLException {
        List<Station> stations = new ArrayList<>();
        String req = "SELECT * FROM station";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while (rs.next()) {
            Station s = new Station();
            s.setId(rs.getInt("id"));
            s.setCapacite(rs.getInt("capacite"));
            s.setLocalisation(rs.getString("localisation"));
            stations.add(s);
        }
        return stations;
    }
    
}