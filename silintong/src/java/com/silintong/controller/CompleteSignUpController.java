/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

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
@WebServlet(name = "CompleteSignUpController", urlPatterns = {"/SignUpCompleted"})
public class CompleteSignUpController extends HttpServlet {

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
            {
        PrintWriter out=null;
        try {
            out = response.getWriter();
        } catch (IOException ex) {
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {            
            out.close();
        }
        
        String fname=request.getParameter("firstname");
        String lname=request.getParameter("lastname");
        String email=request.getParameter("email");
        String pass=request.getParameter("pass");
        String username=request.getParameter("username");
        String sex=request.getParameter("sex");
        String datebday=request.getParameter("datebday");
        String monthbday=request.getParameter("monthbday");
        String yearbday=request.getParameter("yearbday");
        String foto=request.getParameter("foto");
        List<FileItem> items;
        InputStream filecontent = null;
        /*
        try {
            items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
            
            for (FileItem item : items) {
                if(item.getFieldName().equals("foto"))
                {
                        filecontent = item.getInputStream();
                }
            }
        } catch (FileUploadException ex) {
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        */
        String randomFotoFile=UUID.randomUUID().toString()+foto;
        User user=new User(fname, lname, pass, email, username, yearbday, sex, 500, randomFotoFile);
        try {
            FileOutputStream outputStream = 
                        new FileOutputStream(new File(randomFotoFile));
            int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = filecontent.read(bytes)) != -1) {
			outputStream.write(bytes, 0, read);
		}
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        user.insertUser();
        
        
        
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
