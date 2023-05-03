/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author khali
 */
public class MyDB {
    private String url = "jdbc:mysql://localhost:3306/ecovelo";
    private String user = "root";
    private String password = "";
    
    private Connection cnx;
    
    private static MyDB instance;

    private MyDB() {
        try {
            cnx = DriverManager.getConnection(url, user, password);
            System.out.println("connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }
    
    public static MyDB getInstance(){
        if(instance == null)
            instance = new MyDB();
        return instance;
    }

    public Connection getCnx() {
        return cnx;
    }
    
    
    
    
}
