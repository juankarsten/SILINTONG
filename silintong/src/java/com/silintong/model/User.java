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

    public User(String fname,String lname, String pass, String email) {
        this.fname = fname;
        this.pass = pass;
        this.email = email;
        this.lname=lname;
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
