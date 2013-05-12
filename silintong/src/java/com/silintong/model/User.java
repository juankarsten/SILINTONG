/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.model;

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

    public User(String fname,String lname, String pass, String email) {
        this.fname = fname;
        this.pass = pass;
        this.email = email;
        this.lname=lname;
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
    
}
