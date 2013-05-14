/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.db.DBConnector;
import com.silintong.model.Answer;
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

/**
 *
 * @author GG
 */
@WebServlet(name = "QuestionDetail", urlPatterns = {"/QuestionDetail"})
public class QuestionDetail extends HttpServlet {

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
            String namakategori = request.getParameter("namakategori");
            String poster = request.getParameter("userposter");
            String judul =request.getParameter("qtitle");
            String idpertanyaan = request.getParameter("idpertanyaan");
            String konten = request.getParameter("konten");
            String deadline = request.getParameter("duedate");
            String poin =request.getParameter("poin");
            ArrayList<String> QuestionDetail = new ArrayList<String>();
            QuestionDetail.add(judul);
            QuestionDetail.add(poster);
            QuestionDetail.add(konten);   
            QuestionDetail.add(deadline);
            QuestionDetail.add(namakategori);
            QuestionDetail.add(poin);
            DBConnector db = new DBConnector();
            ResultSet rs = db.getAnswer(idpertanyaan);
            ArrayList<Answer> listofAnswer = new ArrayList<Answer>();
            while (rs.next()) {
                String idanswer = ""+rs.getObject(1);
                String content = ""+rs.getObject(2);
                String username = ""+rs.getObject(9);
                String idquestion = ""+rs.getObject(4);
                String isapproved = ""+rs.getObject(5);
                String dateposted = ""+rs.getObject(6);
                String filename = ""+rs.getObject(7);                     
                Answer answer = new Answer(idanswer,content,username,idquestion,isapproved, dateposted, filename);
                listofAnswer.add(answer);
            }
            request.setAttribute("questiondetail", QuestionDetail);
            request.setAttribute("answers", listofAnswer);
            RequestDispatcher view = request.getRequestDispatcher("questiondetail.jsp");
            out.print(QuestionDetail);
            
            //view.forward(request, response);
        } 
        catch(Exception e) {            
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
