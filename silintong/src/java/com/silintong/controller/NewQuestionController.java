/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.silintong.controller;

import com.silintong.db.DBConnector;
import com.silintong.model.Question;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
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
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String tanggalhariini= dateFormat.format(date).toString();
            DBConnector db = new DBConnector();
            ResultSet idkat = db.getIdKategori(kategori);
            ResultSet idusername = db.getIdUsername(username);
            while (idkat.next() && idusername.next()) {
                String idkategori = idkat.getObject("idcategory").toString(); 
                String iduser = idusername.getObject("iduser").toString();
                int isfalse = 0;
                Boolean checkIQ = db.insertQuestion(judul,isi,iduser,isfalse,tanggalhariini,deadline, poin,idkategori,filetambahan);
                if (!checkIQ) {
                    ArrayList<Question> listOfQuestions = new ArrayList<Question>();
                    ResultSet resultSet = db.getLatestQuestions();
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
                    response.sendRedirect("postquestion.jsp");
                }
            }       
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
