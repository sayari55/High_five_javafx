/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Don;
import java.util.List;

/**
 *
 * @author MSI
 */
public interface InterfaceDon {
    
    
    public void ajouter(Don i) ;
    public void modifier (String nom , String email ,  String type , String motif , int id) ;
    public Boolean supprimer ( int id) ;
    public List<Don> afficher() ;
    
}
