/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.model;

import com.silintong.db.DBConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author juan.karsten
 */
public class User {
    private String fname;
    private String lname;
    private String pass;
    private String email;
    private String username;
    private String bday;
    private String sex;
    private int point;
    private String foto;

    public User(String username, String pass, String email) {
        this.username=username;
        this.email = email;
        this.lname=lname;
    }
    
    public User(String fname,String lname, String pass, String email) {
        this.fname=fname;
        this.lname=lname;
        this.email = email;
        this.lname=lname;
    }

    public User(String fname, String lname, String pass, String email, String username, String bday, String sex, int point, String foto) {
        this.fname = fname;
        this.lname = lname;
        this.pass = pass;
        this.email = email;
        this.username = username;
        this.bday = bday;
        this.sex = sex;
        this.point = point;
        this.foto = foto;
    }
    
    

    public String getBday() {
        return bday;
    }

    public String getFoto() {
        return foto;
    }

    public int getPoint() {
        return point;
    }

    public String getSex() {
        return sex;
    }

    public String getUsername() {
        return username;
    }
    
    

    public String getLname() {
        return lname;
    }
    
    

    public String getEmail() {
        return email;
    }

    public String getFName() {
        return fname;
    }

    public String getPass() {
        return pass;
    }

    public void setBday(String bday) {
        this.bday = bday;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFName(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }
    
    

    public void setPass(String pass) {
        this.pass = pass;
    }
    
    public void insertUser(){
        User user=this;
        DBConnector dBConnector=new DBConnector();
        Connection dbConnection=dBConnector.getConnection();
        String insertTableSQL = "INSERT INTO USER VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement statement=dbConnection.prepareStatement(insertTableSQL);
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPass());
            statement.setString(4, user.getFName());
            statement.setString(5, user.getLname());
            statement.setString(6, user.getBday());
            statement.setString(7, user.getSex());
            statement.setInt(8, user.getPoint());
            statement.setString(9, user.getFoto());
            statement.execute();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        dBConnector.closeConnection();
        
    }
}
