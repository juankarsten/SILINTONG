/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.model;

/**
 *
 * @author maldy.vinandar
 */
public class Answer {
    private String idanswer;
    private String content;
    private String idusername;
    private String idquestion;
    private String isapproved;
    private String dateposted;
    private String filename;

    public Answer(String idanswer, String content, String idusername, String idquestion, String isapproved, String dateposted, String filename) {
        this.idanswer = idanswer;
        this.content = content;
        this.idusername = idusername;
        this.idquestion = idquestion;
        this.dateposted = dateposted;
        this.isapproved = isapproved;
        this.filename = filename;
    }
    
    public Answer(String content, String idusername, String idquestion, String isapproved, String dateposted, String filename) {
        this.idanswer = idanswer;
        this.content = content;
        this.idusername = idusername;
        this.idquestion = idquestion;
        this.dateposted = dateposted;
        this.isapproved = isapproved;
        this.filename = filename;
    }
    
    public String getIdanswer(){
        return this.idanswer;
    }
    
    public String getContent(){
        return this.content;
    }
    
    public String getIdusername(){
        return this.idusername;
    }
    
    public String getIdQuestion(){
        return this.idquestion;
    }
    
    public String getIsapproved(){
        return this.isapproved;
    }
    
    public String getDateposted(){
        return this.dateposted;
    }
    
    public String getFilename(){
        return this.filename;
    }
}