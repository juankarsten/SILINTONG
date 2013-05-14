<%-- 
    Document   : editprofile
    Created on : May 14, 2013, 4:22:27 PM
    Author     : juan.karsten
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="com.silintong.model.User"%>
<%@page import="com.silintong.extra.Validator"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.silintong.db.DBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Update Profile</h1>
        <%
            Enumeration usersession = session.getAttributeNames();
            if(usersession == null) {
                response.sendRedirect("index.jsp");
            }
//            String username=(String)session.getAttribute("username");
            String username = usersession.nextElement().toString();
            if(!Validator.isExist(username)){
                response.sendRedirect("index.jsp");
            }
            User user=new DBConnector().searchUsername(username);
         %>
         
         <form action="" method="post">
             <div>
                 Password: 
                 <input type="password" name="pass" value=<%= user.getPass() %> />
             </div>
              <div>
                 First Name:  
                 <input type="text" name="fname" value=<%= user.getFName()%> />
             </div>
              <div>
                 Last Name: 
                 <input type="text" name="lname" value=<%= user.getLname() %> />
             </div>
             <div>
                 Birthday: 
                 <input type="text" name="bday" value=<%= user.getBday() %> />
             </div>
             
             <div>
                 Sex: 
                 <%
                     boolean jk[]=new boolean[2];
                     if(user.getSex().equals("male")){
                         jk[0]=true;
                     }else{
                         jk[1]=true;
                     }
                 %>
                 Male: <input type="radio" name="sex" value="male" checked=<%= jk[0] %>/>
                 Female: <input type="radio" name="sex" value="female" checked=<%= jk[1] %>/>
             </div>
             <div>
                 Foto User:
                 <% if(Validator.isExist(user.getFoto())){%>
                    <img src=<%=user.getFoto()%> />
                 <%}%>
                 <input type="file" name="foto"  />
                 
             </div>
             <div>
                 email: 
                 <input type="text" name="email" value=<%= user.getEmail() %> />
             </div>
             <input type="submit" value="update"/>
         </form>
         
    </body>
</html>
