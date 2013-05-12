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
    
    public void insertUser(User user){
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
        
    }
}
