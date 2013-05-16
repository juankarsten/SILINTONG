<%-- 
    Document   : rate.jsp
    Created on : May 15, 2013, 6:32:57 AM
    Author     : juan.karsten
--%>

<%@page import="com.silintong.db.DBConnector"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.silintong.model.Question"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.silintong.model.Rating"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            String rate=request.getParameter("rate");
            String ans=request.getParameter("ans");
            String user=request.getParameter("user");
            DBConnector db = new DBConnector();
            ResultSet rs = db.getIdUsername(user);
            while (rs.next()){
                String iduser = rs.getObject(1).toString();
                Rating rating=new Rating(iduser, ans, rate);
                rating.insertRate(out);

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
                            String users = ""+resultSet.getObject(8);
                            Question qst = new Question(idQuestion,title,content,null,null,dateposted,duedate,Integer.parseInt(point),nameCategory,null);
                            qst.setUsername(users);
                            listOfQuestions.add(qst);
                        }
                    request.setAttribute("latestQuestion", listOfQuestions);
                    RequestDispatcher view = request.getRequestDispatcher("home.jsp");
                    view.forward(request, response);
            }
        %>
    </body>
</html>
