/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.db;

import com.silintong.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author juan.karsten
 */
public class DBConnector {
    private Connection dbConnection;

    public DBConnector() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            dbConnection=DriverManager.getConnection(DBSettings.URL, DBSettings.USER, DBSettings.PASSWORD);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
    
    public Connection getConnection(){
        return dbConnection;
    }
    
    public void closeConnection(){
        try {
            
            dbConnection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet getLatestQuestions() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven FROM QUESTION q,CATEGORY c WHERE q.idcategory=c.idcategory ORDER BY dateposted DESC LIMIT 0 , 10";
         Statement statement = dbConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public ResultSet login(String username, String password) throws SQLException{
        String query = "select * from user where username ='" + username + "' and password='" + password + "'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public User searchUsername(String username) throws SQLException{
        String query = "select * from user where username ='" + username + "' ";
        Statement statement = dbConnection.createStatement();
        ResultSet rs = statement.executeQuery(query); 
        User user;
        while (rs.next()) {
            
           String id = rs.getObject(1)+"";
           String uname = rs.getObject(2)+"";
           String pass=rs.getObject(3)+"";
           String fname= rs.getObject(4)+"";
           String lname= rs.getObject(5)+"";
           String bday= rs.getObject(6)+"";
           String sex=rs.getObject(7)+"";
           int poin=Integer.parseInt(rs.getObject(8)+"");
           String fotouser=rs.getObject(9)+"";
           String email=rs.getObject(10).toString();
           return new User(fname, lname, pass, email, username, bday, sex, poin, fotouser);
            
        }
        
        return null;
    }
}
