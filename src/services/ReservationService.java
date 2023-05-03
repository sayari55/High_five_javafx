/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import entities.Reservation;
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
public class ReservationService implements IService<Reservation>  {
    Connection cnx;

    public ReservationService() {
        cnx = MyDB.getInstance().getCnx();
    }
     @Override
    public void ajouter(Reservation t) throws SQLException {
        String req = "insert into reservation(date_debut,date_fin ,station_id) values('" + t.getDatedebut() + "','" + t.getDatefin() +" ',"+t.getStation_id()+")";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
    }
     
   @Override
    public boolean supprimer(Reservation t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from reservation where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
            System.out.println("reservation supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;

    }
    @Override
    public void modifier(Reservation res) throws SQLException, ParseException {
        String query = "UPDATE  reservation set date_debut=?,date_fin=? ,etat=? Where id ='" + res.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setDate(1,(Date)res.getDatedebut());
            ste.setDate(2,(Date) res.getDatefin());
            ste.setInt(3, res.getEtat());
            
            ste.executeUpdate();
            System.out.println("La reservation a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
     @Override
public List<Reservation> afficher() throws SQLException {
    List<Reservation> reservations = new ArrayList<>();
    String U = "SELECT r.id, r.date_debut, r.date_fin, r.etat, s.localisation as st " +
               "FROM reservation r " +
               "JOIN station s ON r.station_id = s.id";
    Statement st = cnx.createStatement();
    ResultSet re =  st.executeQuery(U);
    while(re.next()){
        Reservation res = new Reservation(
            re.getInt("id"),
            re.getDate("date_debut"),
            re.getDate("date_fin"),
            re.getInt("etat"),
            re.getString("st")
        );
            System.out.println(res);
        reservations.add(res);

    }
    return reservations;
}
 public void valider(Reservation res) throws SQLException, ParseException {
        String query = "UPDATE  reservation set etat=1 Where id ='" + res.getId() + "'";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            
            ste.executeUpdate();
            System.out.println("La reservation a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

}
