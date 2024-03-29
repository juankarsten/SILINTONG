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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
         PreparedStatement statement = dbConnection.prepareStatement(query);
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public ResultSet getQuestions(String username,String password,int limitfrom,int limitto,int idCategory) throws SQLException{
        ResultSet rs=this.login(username, password);
        int count=0;
        int idUsername=0;
        while(rs.next()){
            if(rs.getObject(1)==null)return null;
            
            idUsername=Integer.parseInt(rs.getObject(1)+"");
            count++;
        }
        if(count==0)return null;
        
        String query = "SELECT idquestion, title, content, dateposted, duedate,isanswered FROM QUESTION q WHERE q.idcategory=?  ORDER BY dateposted DESC LIMIT ? , ?";
        PreparedStatement statement = dbConnection.prepareStatement(query);
        statement.setInt(1, idCategory);
        statement.setInt(2, limitfrom);
        statement.setInt(3, limitto);
        ResultSet resultSet = statement.executeQuery(); 
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
    
    public String insertAnswerForService(String username, String password,String content, String idQuestion){
        ResultSet rs;
        int idUsername=0;
        try {
            rs = this.login(username, password);
            int count=0;
            
            while(rs.next()){
                if(rs.getObject(1)==null)return null;

                idUsername=Integer.parseInt(rs.getObject(1)+"");
                count++;
            }
            if(count==0)return "unauthorized user";
        
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return "unauthorized user";
        }
        
        
        DateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String dateposted=dateFormat.format(Calendar.getInstance().getTime());
        String filename="";
        String query2="INSERT INTO answer (content,idusername,idquestion,isapproved,dateposted,filename) VALUES ('"+content+"','"+idUsername+"','"+idQuestion+"','0','"+dateposted+"','"+filename+"')";
        Statement statement;
        try {
            statement = dbConnection.createStatement();
            String hasil=statement.execute(query2)+"";
            if(hasil.equals("false")){
                return "berhasil";
            }else{
                return "Ada kesalahan di server. Coba hubungin admin.";
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnector.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }
    }
    

    public ResultSet getEducationCategory() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idcategory = '1' ORDER BY dateposted DESC LIMIT 0 , 10";
         Statement statement = dbConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    
    
    public ResultSet getAnswer(String idquestion) throws SQLException{
        String query ="SELECT * FROM  answer  an , user u WHERE an.idquestion='"+idquestion+"' and "
                + "an.idusername = u.iduser";
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
        String query="select * from user order by poin desc";
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
    
    public ResultSet getPaybroCategory() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idcategory = '4' ORDER BY dateposted DESC LIMIT 0 , 10";
         Statement statement = dbConnection.createStatement();
         ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public ResultSet getRakoonCategory() throws SQLException{
        String query = "SELECT idquestion,namecategory, title, content, dateposted, duedate,pointgiven,username FROM QUESTION q,CATEGORY c, USER u WHERE q.idcategory=c.idcategory AND q.idusername=u.iduser AND q.idcategory = '5' ORDER BY dateposted DESC LIMIT 0 , 10";
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
    
    public boolean insertQuestion(String judul, String isi,String iduser, int isfalse, String tanggalhariini, String deadline, String poin, String idkategori, String filetambahan) throws SQLException{
        String query ="INSERT INTO Question (title,content,idusername,isanswered,dateposted,duedate,pointgiven,idcategory,filename) VALUES ('"+judul+"','"+isi+"','"+iduser+"','"+isfalse+"','"+tanggalhariini+"','"+deadline+"','"+poin+"','"+idkategori+"','"+filetambahan+"')";
        Statement statement = dbConnection.createStatement();
        Boolean resultSet = statement.execute(query);
        return resultSet;
    }
    
    public ResultSet getPoinUser(String username) throws SQLException {
        String query = "SELECT poin from user where username='"+username+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query); 
        return resultSet;
    }
    
    public void cutPoint(String username, int poin) throws SQLException {
        ResultSet rs = getIdUsername(username);
        ResultSet ra = getPoinUser(username);
        if (rs.next() && ra.next()){
            String iduser =rs.getObject(1).toString();
            int poinuser = Integer.parseInt(ra.getObject(1).toString());
            String updateTableSQL ="update user set poin = ("+poinuser+"-"+poin+")where iduser = '"+iduser+"'";
            PreparedStatement statement;
            statement = dbConnection.prepareStatement(updateTableSQL);
            statement.executeUpdate();        
        }     
    }
    
    public void addPoint(String username, int poin ) throws SQLException {
        ResultSet rs = getIdUsername(username);
        ResultSet ra = getPoinUser(username);
        if (rs.next() && ra.next()){
            String iduser =rs.getObject(1).toString();
            int poinuser = Integer.parseInt(ra.getObject(1).toString());
            poinuser = poinuser + poin;
            
            String updateTableSQL ="update user set poin = ("+poinuser+")where iduser = '"+iduser+"'";
            PreparedStatement statement;
            statement = dbConnection.prepareStatement(updateTableSQL);
            statement.executeUpdate();        
        }     
    }
    
    public void doTransfer(String userasal, String usertujuan, int poin) throws SQLException{
        cutPoint(userasal,poin);
        addPoint(usertujuan,poin);
    }
    
    public int getRating(String idanswer) throws SQLException {
        String query = "select sum(rate) from rating where idanswer='"+idanswer+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query); 
        if (resultSet.next()) {
            int rating = Integer.parseInt(resultSet.getObject(1).toString());
            if (resultSet.getObject(1).toString() != null) {
                return rating;
            }
            else return 0;
        }
        return 0;
    }
    
    public boolean getAnswerRating(String idanswer) throws SQLException {
        String query = "select * from rating where idanswer='"+idanswer+"'";
        Statement statement = dbConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(query); 
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
}
