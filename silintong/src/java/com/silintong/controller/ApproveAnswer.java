/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.db.DBConnector;
import com.silintong.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GG
 */
@WebServlet(name = "ApproveAnswer", urlPatterns = {"/ApproveAnswer"})
public class ApproveAnswer extends HttpServlet {

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
            String idanswer = request.getParameter("answerid");
            int poin = Integer.parseInt(request.getParameter("poin"));
            String username = request.getParameter("username");
            String usernamepenjawab = request.getParameter("userp");
            DBConnector db = new DBConnector();
            db.updateAnswer(idanswer);
            db.cutPoint(username,poin);
            db.addPoint(usernamepenjawab, poin);
            ResultSet resultSet = db.getLatestQuestions();
            ArrayList<Question> listOfQuestions = new ArrayList<Question>();
            
                while (resultSet.next()) {
                    String idQuestion = ""+resultSet.getObject(1);
                    String nameCategory = ""+resultSet.getObject(2);
                    String title = ""+resultSet.getObject(3);
                    String content = ""+resultSet.getObject(4);
                    String dateposted = ""+resultSet.getObject(5);
                    String duedate = ""+resultSet.getObject(6);
                    String point = ""+resultSet.getObject(7);   
                    String user = ""+resultSet.getObject(8);
                    Question qst = new Question(idQuestion,title,content,null,null,dateposted,duedate,Integer.parseInt(point),nameCategory,null);
                    qst.setUsername(user);
                    listOfQuestions.add(qst);
                }
                request.setAttribute("latestQuestion", listOfQuestions);
                RequestDispatcher view = request.getRequestDispatcher("home.jsp");
                view.forward(request, response);
        } 
        catch (Exception e) {            
            out.print(e);
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
