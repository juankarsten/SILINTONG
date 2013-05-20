/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.db.DBConnector;
import com.silintong.extra.Validator;
import com.silintong.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.el.util.Validation;

/**
 *
 * @author ALdY
 */
@WebServlet(name = "buyPointsPost", urlPatterns = {"/buyPointsPost"})
public class BuyPointsPostController extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            String uname = request.getParameter("username");
            String userPaybro = request.getParameter("userPaybro");
            String passwordPaybro = request.getParameter("passwordPaybro");
            String usernameAccount = "silintong";
            String passwordAccount = "silintong123";
            String description = request.getParameter("answercontent");
            int poin = Integer.parseInt("50000");
            
            boolean result = payWithPayBro(usernameAccount,passwordAccount,userPaybro,passwordPaybro,poin,description);
            if(result){
                DBConnector db = new DBConnector();
                db.addPoint(uname, poin);
            }
            
            RequestDispatcher view=request.getRequestDispatcher("latestQuestions");
            view.forward(request, response);
        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LatestQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LatestQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LatestQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(LatestQuestionsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private static Boolean payWithPayBro(java.lang.String aUName, java.lang.String aPass, java.lang.String cUName, java.lang.String cPass, int amount, java.lang.String description) {
        pb.services.PayBro_Service service = new pb.services.PayBro_Service();
        pb.services.PayBro port = service.getPayBroPort();
        return port.payWithPayBro(aUName, aPass, cUName, cPass, amount, description);
    }

    

}
