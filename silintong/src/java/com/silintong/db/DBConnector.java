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
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser ORDER BY dateposted DESC LIMIT 0 , 10";
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
    
    public ResultSet getIdKategori(String namakategori) throws SQLException {
        String query = "SELECT idcategory from category where namecategory='"+namakategori+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public ResultSet getIdUsername(String username) throws SQLException {
        String query = "SELECT iduser from user where username='"+username+"'";
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

           String email=rs.getObject(10)+"";
           return new User(fname, lname, pass, email, username, bday, sex, poin, fotouser,id);
            
        }
        return null;
    }
    public ResultSet getMyQuestions(String username) throws SQLException{
        String query ="SELECT iduser FROM user WHERE username='"+username+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);                                          
        String idusername = null;
        while(resultSet.next()){
            idusername = resultSet.getObject(1).toString();
        }
        String query2 = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idusername = '"+idusername+"'ORDER BY dateposted DESC LIMIT 0 , 15";
        statement = dbConnection.createStatement();
        
        resultSet = statement.executeQuery(query2); 
        return resultSet;
    }
    
    public void insertAnswer(String content, String username, String idQuestion, String dateposted, String filename) throws SQLException{
        String query ="SELECT iduser FROM user WHERE username='"+username+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);                                          
        String idusername = null;
        while(resultSet.next()){
            idusername = resultSet.getObject(1).toString();
        }
        String query2="INSERT INTO answer (content,idusername,idquestion,isapproved,dateposted,filename) VALUES ('"+content+"','"+idusername+"','"+idQuestion+"','0','"+dateposted+"','"+filename+"')";
        statement = dbConnection.createStatement();
        statement.execute(query2); 
    }
    
    public ResultSet getAnswer(String idquestion) throws SQLException{
        String query ="SELECT * FROM answer as an join user as u on an.idusername = u.iduser WHERE idquestion='"+idquestion+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
    
}
