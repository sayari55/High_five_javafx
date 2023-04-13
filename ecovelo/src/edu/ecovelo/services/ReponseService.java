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
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.ecovelo.entities.reponse;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author benza
 */
public class ReponseService {
     private Connection conn;
    private String requete;

    public ReponseService() {
        conn = MyConnection.getInstance().getCnx();
    }
    
    public void insert(reponse r) {
        String requete = "INSERT INTO reponse (id_reclamation_id,date_reponse,sujet_reponse,motif,avis) VALUES (?,?,?,?,?)";
        
        
        try {
           
            

           
            PreparedStatement rec = conn.prepareStatement(requete, Statement.RETURN_GENERATED_KEYS);
 java.util.Date currentDate = new java.util.Date();
            rec.setInt(1, r.getId_reclamation_id());
           rec.setDate (2, new java.sql.Date(currentDate.getTime()));
            rec.setString(3, r.getSujet_reponse());
            rec.setString(4, r.getMotif());
             
            rec.setInt(5,0);
                        

            

          
            rec.executeUpdate();

            ResultSet rs = rec.getGeneratedKeys();
            if (rs.next()) {
                int repID = rs.getInt(1);
                r.setId_reponse(repID);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public List<reponse> readAll() {
        String requete = "SELECT reponse.* FROM reponse ";
        List<reponse> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {

                reponse r;
                
                r = new reponse(rs.getInt("id_reponse"),rs.getInt("id_reclamation_id"), rs.getDate("date_reponse"), rs.getString("sujet_reponse"), rs.getString("motif"));
                list.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
        public void delete(int id_rep) {
   
    String requete = "DELETE FROM reponse WHERE id_reponse = ?";
    try {
        PreparedStatement ps = conn.prepareStatement(requete);
        ps.setInt(1, id_rep);
        ps.executeUpdate();
    } catch (SQLException ex) {
        Logger.getLogger(ReclamationService.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
}
