/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author GG
 */
@WebServlet(name = "NewQuestionController", urlPatterns = {"/NewQuestionController"})
public class NewQuestionController extends HttpServlet {

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
            
            
            //String username = "Johny";
            String username = request.getParameter("username");
            String judul=request.getParameter("judul");
            String isi=request.getParameter("isipertanyaan");
            String deadline=request.getParameter("deadline");
            String poin=request.getParameter("poin");
            String kategori = request.getParameter("kategori");
            String filetambahan=request.getParameter("filetambahan");
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            String tanggalhariini= dateFormat.format(date).toString();
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/silintong", "root", "toor");
            Statement st = con.createStatement();
            ResultSet idkat = st.executeQuery("SELECT idcategory from category where namecategory='"+kategori+"'");
            String idkategori = idkat.getString("idcategory");
            out.println(idkategori); 
            Boolean checkIQ;
            checkIQ = st.execute("INSERT INTO Question values ("+judul+","+isi+","+username+","+0+","+tanggalhariini+","+deadline+","+poin+","+idkategori+","+filetambahan+","+")");
            if (checkIQ) {
                response.sendRedirect("home.jsp");
            }    
            else {
                response.sendRedirect("postquestion.jsp");
            }
        }
        catch (Exception e) {            
            
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
