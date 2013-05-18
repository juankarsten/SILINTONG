/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.service;

import com.silintong.db.DBConnector;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

/**
 * REST Web Service
 *
 * @author juan.karsten
 */
@Path("lintongservices")
public class LintongservicesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LintongservicesResource
     */
    public LintongservicesResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.silintong.service.LintongservicesResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getJson() {
        //TODO return proper representation object
        return "test";
    }
    
    /**
     * Retrieves representation of an instance of
     * com.silintong.service.LintongservicesResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("answer/{username}/{password}/{content}/{idQuestion}")
    @Produces("application/json")
    public String answer(@PathParam("username") String username,@PathParam("password") String password,
    @PathParam("content") String content, @PathParam("idQuestion") String idQuestion){
       return  new DBConnector().insertAnswerForService(username, password, content, idQuestion);
    }

    /**
     * Retrieves representation of an instance of
     * com.silintong.service.LintongservicesResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("seequestion/{username}/{password}/{idcategory}/{limitfrom}/{limitto}")
    @Produces("application/json")
    public String getUnansweredQuestion(@PathParam("username") String username,
            @PathParam("password") String password, @PathParam("limitfrom") int limitfrom, @PathParam("limitto") int limitto
            , @PathParam("idcategory") int idCategory) {
        ResultSet rs;
        String hasil="";
        JSONArray ja=new JSONArray();
        try {
            rs=new DBConnector().getQuestions(username, password, limitfrom, limitto, idCategory);
            String[] keys={"idquestion","title","content","dateposted","duedate","isanswered"};
            JSONObject jo;
            while(rs.next()){
                jo=new JSONObject();
                for(int ii=1;ii<=6;ii++){
                    String temp=rs.getObject(ii).toString();
                    jo.put(keys[ii-1],temp);
                }
                
                ja.put(jo);
            }
            
        } catch (SQLException ex) {
            return "ada kesalahan."+ex;
            
        } catch (JSONException ex) {
            return "ada kesalahan."+ex;
        }catch(Exception ex){
            return "ada kesalahan."+ex;
        }
        
        return ja.toString();
    }

    /**
     * PUT method for updating or creating an instance of
     * LintongservicesResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putJson(String content) {
    }
}
