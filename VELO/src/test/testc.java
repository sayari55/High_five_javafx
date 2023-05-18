/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import entities.Don;
import entities.DonVelo;
import java.sql.SQLException;
import services.DonCrud;
import services.DonVeloCrud;


/**
 *
 * @author MSI
 */
public class testc {
    
    
    
    public static void main(String[] args) {
        
        
         



    
         DonVeloCrud dn = new DonVeloCrud();
     //    DonVelo i = new DonVelo(4,25, "kbir", "test" , "khj","ghjg" );
     //dn.modifier(4,25,"test","test","test","test");
         
     //   dn.supprimer(1);
   //      ic.modifier("omar", "saya", "motreb", "omaaaaaaaaar" , 6  );
           dn.supprimer(4);
     //      System.out.println(ic.afficher());
         // dn.ajout(i);



    }
    
    
    
    
}







