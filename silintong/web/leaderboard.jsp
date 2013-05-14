<%-- 
    Document   : leaderboard.jsp
    Created on : May 15, 2013, 1:40:25 AM
    Author     : juan.karsten
--%>

<%@page import="com.silintong.db.DBConnector"%>
<%@page import="java.sql.ResultSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table >
        <%
                out.print("<tr>");
                out.print("<td>Nama</td>");
                out.print("<td>Poin</td>");
                out.print("</tr>");
            ResultSet rs=new DBConnector().getLeaderBoard();
            int ii=0;
            while(rs.next()){
                if(ii>=5)break;
                String name=rs.getObject(4)+" "+rs.getObject(5);
                String poin=rs.getObject(8)+"";
                out.print("<tr>");
                out.print("<td>"+name+"</td>");
                out.print("<td>"+poin+"</td>");
                out.print("</tr>");
                ii++;
            }
            %>
        </table>
    </body>
</html>
