<%-- 
    Document   : completesignup
    Created on : May 12, 2013, 7:47:53 PM
    Author     : juan.karsten
--%>

<%@page import="com.silintong.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complete Your Profile</title>
    </head>
    <body>
        <%
            User user=(User)request.getAttribute("user");
        %>
        
        <h1>Complete Your Profile</h1>
        <form action="CompleteSignUpController" method="post">
            <div>
                First Name:<input type="text" name="firstname" value=<%=user.getFName()%> />
                Last Name:<input type="text" name="lastname" value=<%=user.getLname()%> />
            </div>
            <div>
                username:<input type="text" name="username"  value=<%=user.getFName()+"."+user.getLname()%> />
            </div>
            <div>
                Email: <input type="email" name="email" value=<%=user.getEmail()%> />
            </div>
            <div>
                <div>
                    Sex:
                </div>
                <div>
                    <input type="radio" name="sex" value="male"/>Male
                    <input type="radio" name="sex" value="female"/>Female
                </div>
            </div>
            <div>
                Birthday: <input type="date" name="bday">
            </div>
            <div>
                <input type="submit"/>
            </div>
        </form>
    </body>
</html>
