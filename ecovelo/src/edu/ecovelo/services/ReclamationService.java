/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.services;

import edu.ecovelo.entities.reclamation;
import edu.ecovelo.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mohamed Sayari
 */
public class ReclamationService {
    private Connection conn;
    private String requete;

    public ReclamationService() {
        conn = MyConnection.getInstance().getCnx();
    }
    
    public void insert(reclamation r) {
        String requete = "INSERT INTO reclamation (nom,email,sujet,description,date_reclamation,etat,id_util) VALUES (?,?,?,?,?,?,?)";
        
        
        try {
           
            

           
            PreparedStatement rec = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
 java.util.Date currentDate = new java.util.Date();
            rec.setString(1, r.getNom());
            rec.setString(2, r.getEmail());
            rec.setString(3, r.getSujet());
            rec.setString(4, r.getDescription());
             rec.setDate (5, new java.sql.Date(currentDate.getTime()));
            rec.setString(6, r.getEtat());
                        rec.setInt(7, 1);

            

          
            rec.executeUpdate();

            ResultSet rs = rec.getGeneratedKeys();
            if (rs.next()) {
                int recID = rs.getInt(1);
                r.setId_reclamation(recID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public List<reclamation> readAll() {
        String requete = "SELECT reclamation.* FROM reclamation where id_reclamation <> 1";
        List<reclamation> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                reclamation r;
                
                r = new reclamation(rs.getInt("id_reclamation"),rs.getString("nom"), rs.getString("email"), rs.getString("sujet"), rs.getString("description"), rs.getDate("date_reclamation"), rs.getString("etat"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public List<reclamation> readAllAdmin() {
        String requete = "SELECT reclamation.* FROM reclamation ";
        List<reclamation> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                reclamation r;
                
                r = new reclamation(rs.getInt("id_reclamation"),rs.getString("nom"), rs.getString("email"), rs.getString("sujet"), rs.getString("description"), rs.getDate("date_reclamation"), rs.getString("etat"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
//    public void delete(int id_rec) {
//
//        String requete = "DELETE FROM reclamation WHERE id_reclamation = ?";
//        try {
//            PreparedStatement ps = conn.prepareStatement(requete);
//            ps.setInt(1, id_rec);
//            ps.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    
   
     public  void updateRec( reclamation rec) {
         try {
            PreparedStatement ps = conn.prepareStatement("UPDATE reclamation SET description = ? WHERE id_reclamation = ?");

            ps.setString(1, rec.getDescription());
            ps.setInt(2, rec.getId_reclamation());
     
            

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    public void delete(int id) {
   
    String requete = "DELETE FROM reclamation WHERE id_reclamation = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
}
 public reclamation readBynom(String nom) {
        reclamation rec = null;
        requete = "SELECT reclamation.* FROM reclamation where nom= ?";
        try (PreparedStatement stmt = conn.prepareStatement(requete)) {
            stmt.setString(1, nom);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                

      rec = new reclamation(rs.getInt("id_reclamation"),rs.getString("nom"), rs.getString("email"), rs.getString("sujet"), rs.getString("description"), rs.getDate("date_reclamation"), rs.getString("etat"));}
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rec;
    }
}
    
    

    
  

    
   
