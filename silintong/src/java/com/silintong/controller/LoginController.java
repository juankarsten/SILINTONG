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
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "LoginController", urlPatterns = {"/home"})
public class LoginController extends HttpServlet {

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
         try{
            String username = request.getParameter("namauser");
            String password = request.getParameter("katasandi");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            PrintWriter out = response.getWriter();
            DBConnector db = new DBConnector();
            ResultSet rs = db.login(username, password);
            int count = 0;
            while (rs.next()) {
                
                    count++;
            }
            rs.first();
            
            if (count > 0) {
                    HttpSession session = request.getSession( true );
                    session.setAttribute(username, username);
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
                        Question qst = new Question(idQuestion,title,content,null,null,dateposted,duedate,Integer.parseInt(point),nameCategory,null);
                        listOfQuestions.add(qst);
                    }

                    request.setAttribute("latestQuestion", listOfQuestions);
                    RequestDispatcher view=request.getRequestDispatcher("home.jsp");
                    view.forward(request, response);
            } 
            
            else {
                    response.sendRedirect("index.jsp");
            }
            db.closeConnection();
            
        }
        catch (Exception e){
            
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
