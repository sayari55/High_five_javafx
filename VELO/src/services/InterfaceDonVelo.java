/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import entities.DonVelo;
import java.util.List;

/**
 *
 * @author Ines
 */
public interface InterfaceDonVelo {
    public void ajout(DonVelo i) ;
    public void modifier (int id, int age, String modele, String etat, String email, String nom) ;
    public Boolean supprimer ( int id) ;
    public List<DonVelo> afficher() ;
}
