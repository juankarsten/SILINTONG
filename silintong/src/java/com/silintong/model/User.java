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
    private String name;
    private String pass;
    private String email;

    public User(String name, String pass, String email) {
        this.name = name;
        this.pass = pass;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPass() {
        return pass;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
    
}
