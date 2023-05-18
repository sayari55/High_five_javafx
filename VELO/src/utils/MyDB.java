/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ihebc
 */
public class MyDB {
    public Connection getCnx() {
        return cnx;
    }
    
    private String url="jdbc:mysql://localhost:3306/ecovelo_don";
    private String user="root";
    private String password="";
    private Connection cnx;
    private static MyDB instance;
    
    private MyDB() {
        try{
            cnx = DriverManager.getConnection(url,user,password);
            System.out.println("connection etablie");
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static MyDB getInstance() {
        if(instance==null)
            instance=new MyDB();
        return instance;
    }
    
    
    
    
}
