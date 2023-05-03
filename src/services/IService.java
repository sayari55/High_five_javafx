/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

/**
 *
 * @author khali
 */
public interface IService<T> {
    
    void ajouter(T t) throws SQLException;
    boolean supprimer(T t) throws SQLException;
    void modifier(T t) throws SQLException, ParseException;
    List<T> afficher() throws SQLException, ParseException ;

    
}
