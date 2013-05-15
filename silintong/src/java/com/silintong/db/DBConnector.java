/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.db;

import com.silintong.model.Question;
import com.silintong.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
    

    public ResultSet getEducationCategory() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idcategory = '1' ORDER BY dateposted DESC LIMIT 0 , 10";
         Statement statement = dbConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    public ResultSet getAnswer(String idquestion) throws SQLException{
        String query ="SELECT * FROM answer as an join user as u on an.idusername = u.iduser WHERE idquestion='"+idquestion+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        return resultSet;
    }
    
    public void updateUser(User user,PrintWriter out) {
//        String query = "INSERT INTO user(password,firstname,lastname,bithday,sex,fotouser,email) values("+user.getPass()
//                +","+user.getFName()+","+user.getLname()+","+user.getBday()+","+user.getSex()+","+user.
//                +")";
        String updateTableSQL = "UPDATE user SET password = ? ,firstname = ? ,lastname = ? , birthday = ? ,"
                + " sex = ?, fotouser = ?, email = ?  WHERE iduser = ?";
        PreparedStatement statement;
        try {
            statement = dbConnection.prepareStatement(updateTableSQL);

            statement.setString(1, user.getPass());
            statement.setString(2, user.getFName());
            statement.setString(3, user.getLname());
            statement.setString(4, user.getBday());
            statement.setString(5, user.getSex());
            statement.setString(6, user.getFoto());
            statement.setString(7, user.getEmail());
            statement.setString(8, user.getId());
            statement.executeUpdate();
         } catch (SQLException ex) {
            out.print(ex);
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ResultSet getLeaderBoard(){
        String query="select * from user order by poin";
        Statement statement;
        try {
            statement = dbConnection.createStatement();
            ResultSet rs=statement.executeQuery(query);
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
           
    }
    
    
    public List<Question> searchQuestion(String search) {
        List<Question> list=new ArrayList<Question>();
        String query = "select * from question where title like '%"+search+"%' or content like '%"+search+"%'";
        Statement statement;
        try {
            statement = dbConnection.createStatement();
            ResultSet rs = statement.executeQuery(query); 
            User user;
            while (rs.next()) {
                String[] str=new String[11];
                for(int ii=0;ii<10;ii++){
                    str[ii]=rs.getObject(ii+1)+"";
                }

               Question q;
                q = new Question(str[0],str[1],str[2],str[3],str[4],str[5],str[6],Integer.parseInt(str[7]),str[8],str[9]);
                list.add(q);
            }
        } catch (SQLException ex) {
            
            
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    public ResultSet getEntertainmentCategory() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idcategory = '2' ORDER BY dateposted DESC LIMIT 0 , 10";
         Statement statement = dbConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    public ResultSet getGeneralCategory() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idcategory = '3' ORDER BY dateposted DESC LIMIT 0 , 10";
         Statement statement = dbConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public void updateAnswer(String idanswer) throws SQLException{
        String updateTableSQL ="update answer set isapproved = 1 where idanswer = '"+idanswer+"'";
        PreparedStatement statement;
        statement = dbConnection.prepareStatement(updateTableSQL);
        statement.executeUpdate();
    }
}
