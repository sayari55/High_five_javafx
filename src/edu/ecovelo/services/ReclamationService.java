/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ecovelo.services;

import edu.ecovelo.entities.reclamation;
import edu.ecovelo.utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
        String requete = "INSERT INTO reclamation (nom,email,sujet,description,etat) VALUES (?,?,?,?,?)";
        
        
        try {
           
            

           
            PreparedStatement rec = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);

            rec.setString(1, r.getNom());
            rec.setString(2, r.getEmail());
            rec.setString(3, r.getSujet());
            rec.setString(4, r.getDescription());
            rec.setString(5, r.getEtat());
            
            

          
            rec.executeUpdate();

            ResultSet rs = rec.getGeneratedKeys();
            if (rs.next()) {
                int recID = rs.getInt(1);
                r.setId(recID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void delete(int id_reclamation) {

        String requete = "DELETE FROM reclamation WHERE id = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(requete);
            ps.setInt(1, id_reclamation);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void updateRecrut(reclamation r) {
        try {
            PreparedStatement ps = conn.prepareStatement("UPDATE reclamation SET nom = ?, email = ?, sujet = ?, description = ?, etat = ? WHERE id_reclamation = ?");
            ps.setString(1, r.getNom());
            ps.setString(2, r.getEmail());
            ps.setString(3, r.getSujet());
            ps.setString(4, r.getDescription());
            ps.setString(5, r.getEtat());
          
          

            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public List<reclamation> readAll() {
        String requete = "SELECT reclamation.* FROM reclamation where id <> 1";
        List<reclamation> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                reclamation r;
                
                r = new reclamation(rs.getString("nom"), rs.getString("email"), rs.getString("sujet"), rs.getString("description"), rs.getString("etat"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
}
    
   
