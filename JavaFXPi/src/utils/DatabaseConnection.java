/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

/**
 *
 * @author khaliljebali
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnection  {
//    public Connection databaseLink;
//
//    public Connection getConnection(){
        private static String url = "jdbc:mysql://localhost:3306/ecovelo";
        private static String user = "root";
        private static String password = "" ;

        private static DatabaseConnection instance;
//
//        String url ="jdbc:mysql://localhost/" + databaseName;
//
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            databaseLink = DriverManager.getConnection(url , databaseUser , databasePassword);
//            System.out.println("connexion etablie");
//        }catch (Exception e ){
//            e.printStackTrace();
//        }
//        return databaseLink;
//    }

    private Connection cnx;

    private DatabaseConnection(){
        try{
            cnx = DriverManager.getConnection(url , user , password);
            System.out.println("connexion etablie");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public static DatabaseConnection getInstance(){
        if(instance == null){
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getCnx(){
        return cnx;
    }



}
