/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ecovelojava.entities;

/**
 *
 * @author khaliljebali
 */
public class User {

    private int id , phone , age , reset_code ;
    private String userName , email , password , address , roles , verified;

    public User(int id, int phone, int age, int reset_code, String userName, String email, String password, String address, String roles, String verified) {
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.reset_code = reset_code;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles;
        this.verified = verified;
    }

    public User() {
    }

    public User(int id) {
        this.id = id;
    }
    
    

    public User(int id, int phone, int age, String userName, String email, String password, String address) {
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User(int id, int phone, int age, int reset_code, String userName, String email, String password, String address) {
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.reset_code = reset_code;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User(int phone, int age, String userName, String email, String password, String address) {
        this.phone = phone;
        this.age = age;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public User(int reset_code, String email) {
        this.reset_code = reset_code;
        this.email = email;
    }
    
    
    
    public User(int id ,int phone, int age, String userName, String email, String password, String address , int reset_code) {
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.reset_code = reset_code;
    }

    public User(int id, int phone, int age, int reset_code, String userName, String email, String password, String address, String roles) {
        this.id = id;
        this.phone = phone;
        this.age = age;
        this.reset_code = reset_code;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.roles = roles;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getReset_code() {
        return reset_code;
    }

    public void setReset_code(int reset_code) {
        this.reset_code = reset_code;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getVerified() {
        return verified;
    }

    public void setVerified(String verified) {
        this.verified = verified;
    }
    
    

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", phone=" + phone + ", age=" + age + ", reset_code=" + reset_code + ", userName=" + userName + ", email=" + email + ", password=" + password + ", address=" + address + ", roles=" + roles + ", verified=" + verified + '}';
    }

   
    
    
    
    


    
    
    
}
