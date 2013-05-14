/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.db;

import com.silintong.model.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public ResultSet getMyQuestions(String username) throws SQLException{
        String query1 ="SELECT iduser FROM user WHERE username='"+username+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query1); 
        //String idusername = resultSet.getObject(1).toString();
        //String query = "SELECT * FROM QUESTION q,CATEGORY c WHERE q.idcategory=c.idcategory AND q.idusername = '"+idusername+"'ORDER BY dateposted DESC LIMIT 0 , 15";
        //statement = dbConnection.createStatement();
        //resultSet = statement.executeQuery(query); 
        return resultSet;
    }
}
