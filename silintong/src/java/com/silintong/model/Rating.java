/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.model;

import com.silintong.db.DBConnector;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspWriter;

/**
 *
 * @author juan.karsten
 */
public class Rating {
    String idvoter;
    String idanswer;
    String rate;

    public Rating(String idvoter, String idanswer, String rate) {
        this.idvoter = idvoter;
        this.idanswer = idanswer;
        this.rate = rate;
    }

    public String getIdvoter() {
        return idvoter;
    }

    public String getIdanswer() {
        return idanswer;
    }

    public String getRate() {
        return rate;
    }

    public void setIdvoter(String idvoter) {
        this.idvoter = idvoter;
    }
    
    public static String getRate(String voter, String ans){
        
        DBConnector dBConnector=new DBConnector();
        Connection dbConnection=dBConnector.getConnection();
        String selectTableSQL="select * from RATING where idvoter= ? and idanswer=?";
        String selectTableSQL2="select * from user where username= ? ";
            PreparedStatement statement;
        try {
            statement=dbConnection.prepareStatement(selectTableSQL2);
            statement.setString(1, voter);
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                voter=rs.getObject(1)+"";
            }
            statement.close();
            
            statement = dbConnection.prepareStatement(selectTableSQL);
            statement.setString(1, voter);
            statement.setString(2, ans);
            rs=statement.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
                return rs.getObject(4)+"";
            }
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
        }
            return "gagal";
    }
    
    public int insertRate(JspWriter out){
        Rating rating=this;
        DBConnector dBConnector=new DBConnector();
        Connection dbConnection=dBConnector.getConnection();
        int hasil=-100;
        
        String selectTableSQL2="select * from user where username= ? ";
        
        String selectTableSQL="select * from RATING where idvoter= ? and idanswer=?";
        String updateTableSQL="update rating set rate=? where idvoter= ? and idanswer=?";
        
        String insertTableSQL = "INSERT INTO RATING(idvoter,idanswer,rate) VALUES (?,?,?)";
        try {
            PreparedStatement statement=dbConnection.prepareStatement(selectTableSQL2);
            statement.setString(1, rating.getIdvoter());
            ResultSet rs=statement.executeQuery();
            while(rs.next()){
                rating.setIdvoter(rs.getObject(1)+"");
            }
            statement.close();
            
            statement=dbConnection.prepareStatement(selectTableSQL);
            statement.setString(1, rating.getIdvoter());
            statement.setString(2, rating.getIdanswer());
            rs=statement.executeQuery();
            int count=0;
            while(rs.next()){
                count++;
            }
            statement.close();
            
            if(count==0){
                try {
                    out.print("nol");
                } catch (IOException ex) {
                    Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
                }
                statement=dbConnection.prepareStatement(insertTableSQL);
                statement.setString(1, rating.getIdvoter());
                statement.setString(2, rating.getIdanswer());
                statement.setString(3, rating.getRate());
                hasil=statement.executeUpdate();
                statement.close();
            }
            else{
                try {
                    out.print("more");
                } catch (IOException ex) {
                    Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex);
                }
                statement=dbConnection.prepareStatement(updateTableSQL);
                statement.setString(2, rating.getIdvoter());
                statement.setString(3, rating.getIdanswer());
                statement.setString(1, rating.getRate());
                hasil=statement.executeUpdate();
                statement.close();
            }
            
            } 
            catch (SQLException ex) {
                try {
                    out.print(ex);
                } 
                catch (IOException ex1) {
                    Logger.getLogger(Rating.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        dBConnector.closeConnection();
        return hasil;
    }
}
