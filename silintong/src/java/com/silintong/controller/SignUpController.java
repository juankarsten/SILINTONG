/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.extra.Validator;
import com.silintong.model.User;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.el.util.Validation;

/**
 *
 * @author juan.karsten
 */
@WebServlet(name = "SignUpController", urlPatterns = {"/complete%20signup"})
public class SignUpController extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        
        String fname=request.getParameter("firstname");
        String lname=request.getParameter("lastname");
        String email=request.getParameter("email");
        String pass=request.getParameter("pass");
        String pass2=request.getParameter("pass2");
        if(!Validator.isExist(fname))response.sendRedirect("index.jsp?error=firstname");
        else if(!Validator.isExist(email))response.sendRedirect("index.jsp?error=email");
        else if(!Validator.isExist(pass))response.sendRedirect("index.jsp?error=password");
        else if(!Validator.isExist(pass)||!pass.equals(pass2))response.sendRedirect("index.jsp?error=retype%20password");
        
        User user=new User(fname, lname, pass, email);
        
        request.setAttribute("user", user);
        RequestDispatcher view=request.getRequestDispatcher("completesignup.jsp");
        view.forward(request, response);
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
