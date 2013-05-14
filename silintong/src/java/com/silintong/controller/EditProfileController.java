/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.db.DBConnector;
import com.silintong.extra.Validator;
import com.silintong.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            /* TODO output your page here. You may use following sample code. */
            String username=request.getParameter("username");
            String id=request.getParameter("id");
            String poin=request.getParameter("poin");
            String fname=request.getParameter("fname");
            String lname=request.getParameter("lname");
            String pass=request.getParameter("pass");
            String bday=request.getParameter("bday");
            String sex=request.getParameter("sex");
            String foto=request.getParameter("foto");
            String email=request.getParameter("email");
            String foto2=request.getParameter("foto2");
            
            String photo;
            if(!Validator.isExist(foto)){
                photo=foto2;
            }else{
                photo=foto;
            }
            User user=new User(fname, lname, pass, email, username, bday, sex, Integer.parseInt(poin), foto, id);
            out.println(user.toString());
            DBConnector dBConnector=new DBConnector();
            dBConnector.updateUser(user,out);
            response.sendRedirect("home.jsp");
        } finally {            
            out.close();
        }
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
