/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.model;

/**
 *
 * @author maldy.vinandar
 */
public class Question {
    private String id;
    private String title;
    private String content;
    private String idusername;
    private String isanswered;
    private String dateposted;
    private String duedate;
    private int point;
    private String idcategories;
    private String filename;
    private String username;

    public Question(String title, String content, String idusername, String isanswered, String dateposted, String duedate, String idcategories) {
        this.title = title;
        this.content = content;
        this.idusername = idusername;
        this.isanswered = isanswered;
        this.dateposted = dateposted;
        this.duedate = duedate;
        this.idcategories = idcategories;
    }
    
    public Question(String id,String title,String content,String idusername,String isanswered,String dateposted,String duedate,int point,String idcategories,String filename){
        this.id = id;
        this.title = title;
        this.content = content;
        this.idusername = idusername;
        this.isanswered = isanswered;
        this.dateposted = dateposted;
        this.duedate = duedate;
        this.point = point;
        this.idcategories = idcategories;
        this.filename = filename;
    }
    
    public String getTitle(){
        return this.title;
    }
    
    public String getContent(){
        return this.content;
    }
    public String getidUsername(){
        return this.idusername;
    }
    
    public String getIsanswered(){
        return this.isanswered;
    }
    
    public String getDateposted(){
        return this.dateposted;
    }
    
    public String getDuedate(){
        return this.duedate;
    }
    
    public int getPoint(){
        return this.point;
    }
    
    public String getIdcategories(){
        return this.idcategories;
    }
    
    public String getFilename(){
        return this.filename;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public String getUsername(){
        return this.username;
    }
}
