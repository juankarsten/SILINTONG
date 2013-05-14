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
@WebServlet(name = "MyQuestionsController", urlPatterns = {"/myquestions"})

public class MyQuestionsController extends HttpServlet {

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
            String username = request.getParameter("username");
            DBConnector db = new DBConnector();
            ResultSet resultSet = db.getMyQuestions(username);

            out.print("lalala");
            //ArrayList<Question> listOfQuestions = new ArrayList<Question>();
            
            while (resultSet.next()) {
 
                String idQuestion = ""+resultSet.getObject(1);
                String nameCategory = ""+resultSet.getObject(2);
                String title = ""+resultSet.getObject(3);
                String content = ""+resultSet.getObject(4);
                String dateposted = ""+resultSet.getObject(5);
                String duedate = ""+resultSet.getObject(6);
                String point = ""+resultSet.getObject(7);

                //Question qst = new Question(idQuestion,title,content,null,null,dateposted,duedate,Integer.parseInt(point),nameCategory,null);
                //listOfQuestions.add(qst);
            }
           
            //request.setAttribute("myQuestions", listOfQuestions);
            //RequestDispatcher view=request.getRequestDispatcher("myquestions.jsp");
            out.print("lalalala");
            //view.forward(request, response);*/
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
}
