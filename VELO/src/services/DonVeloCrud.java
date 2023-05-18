
package services;

/**
 *
 * @author Ines
 */


import entities.DonVelo;
import entities.Don;
import utils.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DonVeloCrud implements InterfaceDonVelo {

    Connection cnx2 ;
    
    public DonVeloCrud() {
        cnx2 = MyConnection.getInstance().getCnx();
    }
    
    @Override
    public void ajout(DonVelo i){
        
        try {
            String requete1 = "INSERT INTO don_velo ( email, nom, modele, age, etat) VALUES ( ?,?, ?, ? , ?)" ;
            PreparedStatement pst = cnx2.prepareStatement(requete1);
            pst.setString(1, i.getEmail());
            pst.setString(2, i.getNom());             
            pst.setString(3, i.getModele());
            pst.setInt(4, i.getAge());
            pst.setString(5, i.getEtat());
           // pst.setString(6, i.getMotif());
            pst.executeUpdate();
            System.out.println("Don par velo ajouter");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    }
    
    public void modifier(int id, int age, String modele, String etat, String email, String nom) {
    try {
        String requete1 = " UPDATE don_velo SET nom = ?, email = ?, modele = ?, age = ?, etat = ? WHERE id = ? ";
        PreparedStatement pst = cnx2.prepareStatement(requete1);
        pst.setString(1, nom);
        pst.setString(2, email);
        pst.setString(3, modele);
        pst.setString(5, etat);
        pst.setInt(6, id);
        pst.setInt(4,age);

        pst.executeUpdate();
        System.out.println("DonVelo modifié");
    } catch (SQLException ex) {
        System.err.println(ex.getMessage());
    }
}


    @Override
    public Boolean supprimer(int id) {
        Boolean ok=false;
   try {
            String requete2 = " DELETE FROM don_velo WHERE id = ? " ;
            PreparedStatement pst = cnx2.prepareStatement(requete2);
            pst.setInt(1,id);
            pst.executeUpdate();
            System.out.println("Don velo supprime");
            ok=true;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        
        }
        return ok;
    }

    
    public List<DonVelo> afficher() {
                    List<DonVelo> myList = new  ArrayList<> ();

        try {
            String requete1 = "select * from don_velo " ;
            Statement st = cnx2.createStatement() ;
            ResultSet rs = st.executeQuery(requete1);
            while(rs.next())
            {
                DonVelo i = new  DonVelo() ;
                i.setId(rs.getInt(1));
                i.setNom(rs.getString(2));
                i.setEmail(rs.getString(3));
                i.setModele(rs.getString(4));
                i.setEtat(rs.getString(5));
                i.setAge(rs.getInt(6));

                myList.add(i) ; 
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());        }
    
        return myList ;
    }

    
  
   

    

    
}
