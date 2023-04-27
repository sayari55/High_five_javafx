/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecovelojava.services;

/**
 *
 * @author khaliljebali
 */
import ecovelojava.entities.Avis;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;



public class AvisService implements IService<Avis> {

    Connection cnx;

    public AvisService(){
        cnx = DatabaseConnection.getInstance().getCnx();
    }

//    @Override
//    public void ajouter(Avis t ) throws SQLException{
//        String req = "insert into avis( user_id, description , note , etat) values('\" "
//                + "+ t.getId " 
//                + "+ t.getUserId() " 
//                + "+  t.getDescription()"
////                + "+  t.getNote()"
//                + "+  t.getNote()"
//                +  "t.getEtat()')";
//        Statement st = cnx.createStatement();
//        st.executeUpdate(req);
//    }
    
    
    @Override
    public void ajouter(Avis t) throws SQLException {
        PreparedStatement statement;
//        user.setRoles("[]");
        statement = cnx.prepareStatement("INSERT INTO  Avis (user_id,description,note) VALUES" +
                "( ?,?,?)");
        try {
        statement.setInt(1, t.getUserId());
        statement.setString(2, t.getDescription());
        //statement.setInt(3, user.getSpecialite().getId());
        statement.setInt(3, t.getNote());
//        statement.setInt(4, t.getEtat());
        statement.executeUpdate();
        System.out.println("Ajout réussiii !");
//        statement.setString(4, user.getAdress());
//        statement.setDate(5, (Date) user.getDaten());
        
    } catch (SQLException ex) {
            System.out.println(ex.getMessage());
    }
    }

//    @Override
//    public void modifier(Avis t) throws SQLException {
//        String req = "update avis description = ?, note = ? , etat = ?, where id = ? and user_id = ?";
//        PreparedStatement ps = cnx.prepareStatement(req);
//        ps.setInt(1, t.getUserId());
//        ps.setString(2, t.getDescription());
//        ps.setInt(3, t.getNote());
//        ps.setInt(4, t.getEtat());
//        ps.setInt(5, t.getId());
//        ps.executeUpdate();
//
//    }
    @Override
    public void modifier(Avis t) throws SQLException {
        String query = "UPDATE  avis set description=?,note=?  Where id = ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, t.getDescription());
            ps.setInt(2, t.getNote());
            ps.setInt(3, t.getId());

            ps.executeUpdate();
            System.out.println("Votre Avis a ete modifié avec succés");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public boolean supprimer(Avis t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from avis where id = ? ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
            System.out.println("Avis supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;

    }

    @Override
    public List<Avis> recuperer() throws SQLException {
        List<Avis> avisArray = new ArrayList<>();
        String req = "select * from avis";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Avis a = new Avis();
            a.setId(rs.getInt("id"));
            a.setUserId(rs.getInt("user_id"));
            a.setDescription(rs.getString("description"));
            a.setNote(rs.getInt("note"));
            a.setEtat(rs.getInt("etat"));

            avisArray.add(a);
        }
        return avisArray;
    }


    public List<Avis> recupererById(int id) throws SQLException {
        List<Avis> avisArray = new ArrayList<>();
        String req = "select * from avis where id= ?";
        PreparedStatement st = cnx.prepareStatement(req);
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            Avis a = new Avis();
            a.setId(rs.getInt("id"));
            a.setUserId(rs.getInt("user_id"));
            a.setDescription(rs.getString("description"));
            a.setNote(rs.getInt("note"));
            a.setEtat(rs.getInt("etat"));

            avisArray.add(a);
        }
        return avisArray;
    }
    
    public Avis afficher(Avis t) throws SQLException {
        Avis a = new Avis();
        String query = "select * from avis where user_id= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
//            ps.setString(1, t.getDescription());
//            ps.setInt(2, t.getNote());
            ps.setInt(1, t.getUserId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
//            Avis a = new Avis();
            a.setId(rs.getInt("id"));
            a.setUserId(rs.getInt("user_id"));
            a.setDescription(rs.getString("description"));
            a.setNote(rs.getInt("note"));
            a.setEtat(rs.getInt("etat"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return a;
    }





}

