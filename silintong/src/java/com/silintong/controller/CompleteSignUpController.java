/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response){
        //inisialisasi out
        PrintWriter out=null;
        try {
            out = response.getWriter();
        
        } catch (IOException ex) {
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //cari foto
        List<FileItem> items=null;
        InputStream filecontent = null;
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
        
        //dapatkan semua nilai field
        String fname=getFieldItem(items,"firstname");
        String lname=getFieldItem(items,"lastname");
        String email=getFieldItem(items,"email");
        String pass=getFieldItem(items,"pass");
        String pass2=getFieldItem(items,"pass2");
        String username=getFieldItem(items,"username");
        String sex=getFieldItem(items,"sex");
        String datebday=getFieldItem(items,"datebday");
        String monthbday=getFieldItem(items,"monthbday");
        String yearbday=getFieldItem(items,"yearbday");
        String foto=getFieldItem(items,"foto");
        
        String[] months={
        "January","February","March","April","May","June",
                            "July","August","September","October","November","December"
        };
        for(int ii=0;ii<months.length;ii++){
            if(monthbday.equals(months[ii])){
                monthbday=ii+"";
                
            }
        }
        String randomFotoFile=null;
        
        
        //VALIDATION
        int error=0;
        if(!Validator.isExist(fname))error=1;
        if(!Validator.isExist(email))error+=2;
        if(!Validator.isExist(pass))error+=4;
        if(!Validator.isExist(pass2)||(Validator.isExist(pass2)&&!pass.equals(pass2)))error+=8;
        if(!Validator.isExist(username))error+=16;
        
        
        if(Validator.isExist(foto)){
            randomFotoFile=UUID.randomUUID().toString();
            randomFotoFile+=UUID.randomUUID().toString();
            randomFotoFile=randomFotoFile.substring(0,(randomFotoFile.length()>40)?40:randomFotoFile.length())+".png";
            out.print(randomFotoFile.length());
            
            FileOutputStream outputStream=null;
            try {
                File file=new File(randomFotoFile);
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
                out.print(ex);
                Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                out.print(ex);
                Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        User user=new User(fname, lname, pass, email, username, yearbday+"-"+monthbday+"-"+datebday, sex, 500, randomFotoFile);
        if(error==0)out.print(user.insertUser(out));
        
        
        try {
            if(error==0)response.sendRedirect("index.jsp");
            else response.sendRedirect("completesignup.jsp?error="+error);
        } catch (IOException ex) {
            out.print(ex);
            Logger.getLogger(CompleteSignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        out.close();
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
