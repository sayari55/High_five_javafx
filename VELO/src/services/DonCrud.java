/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Don;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MSI
 */
public class DonCrud implements InterfaceDon {
    
    
    Connection cnx2 ;
    
    public DonCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    
    @Override
    public void ajouter(Don i){
        
        try {
            String requete1 = "INSERT INTO don ( nom, email, type,motif) VALUES ( ?,?, ?, ?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete1);
            pst.setString(1, i.getNom());
            pst.setString(2, i.getEmail());             
            pst.setString(3, i.getType());
            pst.setString(4, i.getMotif());
            pst.executeUpdate();
            System.out.println("Don ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    
    public void modifier(String nom, String email, String type, String motif, int id) {
    try {
        String requete1 = " UPDATE don SET nom = ?, email = ?, type = ?, motif = ? WHERE id = ? ";
        PreparedStatement pst = cnx2.prepareStatement(requete1);
        pst.setString(1, nom);
        pst.setString(2, email);
        pst.setString(3, type);
        pst.setString(4, motif);
        pst.setInt(5, id);
        pst.executeUpdate();
        System.out.println("Don modifié");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    @Override
    public Boolean supprimer(int id) {
        Boolean ok=false;
   try {
            String requete2 = " DELETE FROM don WHERE id = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Don supprime");
            ok=true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        
        }
        return ok;
    }

    
    @Override
    public List<Don> afficher() {
                    List<Don> myList = new  ArrayList<> ();

        try {
            String requete1 = "select * from don " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete1);
            while(rs.next())
            {
                Don i = new  Don() ;
                i.setId(rs.getInt(1));
                i.setNom(rs.getString(2));
                i.setEmail(rs.getString(3));
                i.setType(rs.getString(4));
                i.setMotif(rs.getString(5));

                myList.add(i) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }

   

    

    
    
    
}
