<%-- 
    Document   : index
    Created on : May 11, 2013, 12:58:46 PM
    Author     : juan.karsten
--%>

<%@page import="com.silintong.extra.Validator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="complete%20signup" method="post">
            <div>
                First Name:<input type="text" name="firstname" />
                Last Name:<input type="text" name="lastname" />
            </div>
            <div>
                Email: <input type="email" name="email" />
            </div>
            <div>
                Password: <input type="password" name="pass" />
            </div>
            <div>
                Retype Password: <input type="password" name="pass2" />
            </div>
            <div>
                <input type="submit"/>
            </div>
        </form>
        <%
            String error=request.getParameter("error");
            if(Validator.isExist(error)){
                out.print("Complete "+error);
            }
            %>
    </body>
</html>
