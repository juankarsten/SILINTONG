/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.db.DBConnector;
import com.silintong.extra.Validator;
import com.silintong.model.User;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author juan.karsten
 */
@WebServlet(name = "EditProfileController", urlPatterns = {"/editprofile"})
public class EditProfileController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            //cari foto
            List<FileItem> items=null;
            InputStream filecontent = null;
            try {
                items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                
            } catch (FileUploadException ex) {
                out.print(ex);
            } 
            
            
            String username=getFieldItem(items,"username");
            String id=getFieldItem(items, "id");
            String poin=getFieldItem(items, "poin");
            String fname=getFieldItem(items, "fname");
            String lname=getFieldItem(items, "lname");
            String pass=getFieldItem(items, "pass");
            String bday=getFieldItem(items, "bday");
            String sex=getFieldItem(items, "sex");
            String foto=getFieldItem(items, "foto");
            String email=getFieldItem(items, "email");
            String foto2=getFieldItem(items, "foto2");
            
            String photo;
            if(!Validator.isExist(foto)){
                photo=foto2;
            }else{
                photo=foto;
                FileOutputStream outputStream=null;
                try {
                    for (FileItem item : items) {
                        if(item.getFieldName().equals("foto"))
                        {
                                filecontent = item.getInputStream();
                                break;
                        }
                    }
                    
                    String randomFotoFile=UUID.randomUUID().toString();
                    randomFotoFile+=UUID.randomUUID().toString();
                    randomFotoFile=randomFotoFile.substring(0,(randomFotoFile.length()>40)?40:randomFotoFile.length())+".png";
                    photo=randomFotoFile;
                    
                    File file=new File(photo);
                    outputStream=new FileOutputStream(file);
                    int read = 0;
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    byte[] bytes = new byte[1024];	
                    while ((read = filecontent.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, read);
                    }
                    outputStream.close();
                    filecontent.close();
                } catch (FileNotFoundException ex) {
                    out.print("aaaaaa"+ex);
                    Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    out.print(ex);
                    Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            User user=new User(fname, lname, pass, email, username, bday, sex, Integer.parseInt(poin), photo, id);
            out.println(user.toString());
            DBConnector dBConnector=new DBConnector();
            dBConnector.updateUser(user,out);
            response.sendRedirect("home.jsp");
        } finally {            
            out.close();
        }
    }
    
    public String getFieldItem(List<FileItem> items,String fieldname){
        
            for (FileItem item : items) {
                if(item.getFieldName().equals(fieldname))
                {
                    return item.getString();
                }
            }
           return "gagal";
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
