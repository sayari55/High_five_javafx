/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecovelojava.services;

/**
 *
 * @author khaliljebali
 */
import ecovelojava.services.IService;
import ecovelojava.entities.User;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DatabaseConnection;


import java.sql.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class UserService implements IService<User> {

    Connection cnx;

    public UserService(){
        cnx = DatabaseConnection.getInstance().getCnx();
    }


    
    
    @Override
    public void ajouter(User user) throws SQLException {
        PreparedStatement statement;
//        user.setRoles("[]");
        statement = cnx.prepareStatement("INSERT INTO  user (phone,age,userName,email,password,address) VALUES" +
                "( ?,?,?,?,?,?)");
        try {
        statement.setString(4, user.getEmail());
        statement.setString(5, user.getPassword());
        //statement.setInt(3, user.getSpecialite().getId());
        statement.setInt(1, user.getPhone());
        statement.setString(6, user.getAddress());
//        statement.setDate(5, (Date) user.getDaten());
        statement.setString(3, user.getUserName());
        statement.setInt(2, user.getAge());
//        statement.setString(7, user.getPrenom());
//        statement.setString(8, user.getSexe());

//        statement.setString(9, user.getRoles());

        if (this.verif_cred(user)) {
            statement.executeUpdate();
            System.out.println(this.verif_cred(user));
            System.out.println("Ajout réussiii !");
        } else {
            System.out.println(this.verif_cred(user));
            System.out.println("Ajout non effectué");
        }
    } catch (SQLException ex) {
            System.out.println(this.verif_cred(user));
            ex.printStackTrace();
    }
    }
    
    
    

    @Override
    public void modifier(User t) throws SQLException {
        String req = "update user set userName = ?, email = ?, password = ? , age = ?, phone = ?, address = ? , reset_code = ?  where id = ?";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setString(1, t.getUserName());
        ps.setString(2, t.getEmail());
        ps.setString(3, t.getPassword());
        ps.setInt(4, t.getAge());
        ps.setInt(5, t.getPhone());
        ps.setString(6, t.getAddress());


        ps.setInt(8, t.getId());
        ps.setInt(7, t.getReset_code());
        ps.executeUpdate();
        System.out.println("user modifier");

    }
    
    public void modifierByEmail(User t) throws SQLException {
        String req = "update user set  reset_code = ?  where email = ?";
        PreparedStatement ps = cnx.prepareStatement(req);

        ps.setInt(1, t.getReset_code());
        ps.setString(2 , t.getEmail());
        ps.executeUpdate();
        System.out.println("user modifier");

    }

    @Override
    public boolean supprimer(User t) throws SQLException {
        boolean ok = false;
        try {
            PreparedStatement req = cnx.prepareStatement("delete from user where id = ?  ");
            req.setInt(1, t.getId());
            req.executeUpdate();
            ok = true;
            System.out.println("User supprimé !");
        } catch (SQLException ex) {
            System.out.println("Error in delete " + ex);
        }
        return ok;

    }

    @Override
    public List<User> recuperer() throws SQLException {
        List<User> users = new ArrayList<>();
        String req = "select * from user";
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);
        while(rs.next()){
            User u = new User();
            u.setId(rs.getInt("id"));
            u.setAge(rs.getInt("age"));
            u.setEmail(rs.getString("email"));
            u.setUserName(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setPhone(rs.getInt("phone"));
            u.setAddress(rs.getString("address"));
            u.setVerified(rs.getString("verified"));

            users.add(u);
        }
        return users;
    }



    
    public User Login(String email, String mdp) {
        User u = new User();

        String req = "SELECT * FROM User where (email='" + email + "')";
        try {
            //exec
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String version = "2y";
                int strength = 12; // the strength factor
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(strength,new SecureRandom(version.getBytes()));
                String hmdp=rs.getString("password");
                boolean shih = passwordEncoder.matches(mdp,hmdp);
                if(shih) {
                    
                u.setId(rs.getInt(1));
                u.setAge(rs.getInt("age"));
                u.setUserName(rs.getString("userName"));
                u.setEmail(rs.getString("email"));
                u.setPassword(rs.getString("password"));
                u.setPhone(rs.getInt("phone"));
                u.setAddress(rs.getString("address"));
                u.setRoles(rs.getString("roles"));
                }

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
//        System.out.println("Logged in ");
        if(u.getEmail() != null){
            return u;
        }else{
            System.out.println("User not found");
            return u;
        }
    }


    
    public boolean verif_cred(User u) throws SQLException {
        Connection cnx = DatabaseConnection.getInstance().getCnx();
        String email = "";

        try {

            String req1 = "SELECT * FROM User where email='" + email + "'";


            Statement st1 = cnx.createStatement();
            ResultSet rs1 = st1.executeQuery(req1);

            while ((rs1.next())) {
                email = rs1.getString("email");

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        if (email != "") {
            System.out.println("email exists ");
            return false;

        } else {
            return true;
        }
    }
    
    
    public User afficher(User t) throws SQLException {
        User u = new User();
        String query = "select * from user where id= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setInt(1, t.getId());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            u.setId(rs.getInt("id"));
            u.setAge(rs.getInt("age"));
            u.setEmail(rs.getString("email"));
            u.setUserName(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setPhone(rs.getInt("phone"));
            u.setAddress(rs.getString("address"));
            u.setReset_code(rs.getInt("reset_code"));
            u.setRoles(rs.getString("roles"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return u;
    }
    
    public User afficherByEmail(User t) throws SQLException {
        User u = new User();
        String query = "select * from user where email= ?";
        try {
            PreparedStatement ps = cnx.prepareStatement(query);
            ps.setString(1, t.getEmail());
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            u.setId(rs.getInt("id"));
            u.setAge(rs.getInt("age"));
            u.setEmail(rs.getString("email"));
            u.setUserName(rs.getString("userName"));
            u.setPassword(rs.getString("password"));
            u.setPhone(rs.getInt("phone"));
            u.setAddress(rs.getString("address"));
            u.setReset_code(rs.getInt("reset_code"));
            }
        }catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
            return u;
    }
    
    public void access(User t){
        String query = "";
        if (t.getVerified() == null){
            query = "UPDATE user set verified=1 where id='" + t.getId() + "  '";
        }
        else if(Integer.parseInt(t.getVerified()) == 0){
         query = "UPDATE user set verified=1 where id='" + t.getId() + "  '";
        }else if(Integer.parseInt(t.getVerified()) == 1){
         query = "UPDATE user set verified=0 where id='" + t.getId() + "  '"; 
        }
        try{
            PreparedStatement ste = cnx.prepareStatement(query);
            
            ste.executeUpdate();
            System.out.println("access modified");
            
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    }



}
