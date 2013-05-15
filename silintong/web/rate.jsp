<%-- 
    Document   : rate.jsp
    Created on : May 15, 2013, 6:32:57 AM
    Author     : juan.karsten
--%>

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
            Rating rating=new Rating(user, ans, rate);
            rating.insertRate(out);
            response.sendRedirect("home.jsp");
        %>
    </body>
</html>
