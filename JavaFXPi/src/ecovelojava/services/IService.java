/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package ecovelojava.services;

/**
 *
 * @author khaliljebali
 */
import java.sql.SQLException;
import java.util.List;
import java.text.ParseException;


public interface IService<T> {

    void ajouter(T t) throws SQLException;

    void modifier(T t) throws SQLException;

    boolean supprimer(T t) throws SQLException;

    List<T> recuperer() throws SQLException;
}
