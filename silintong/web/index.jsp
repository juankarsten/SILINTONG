<%-- 
    Document   : index
    Created on : May 11, 2013, 12:58:46 PM
    Author     : juan.karsten
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sign Up</h1>
        <form action="signupcontroller" method="post">
            <div>
                <input type="text" name="fullname" />
            </div>
            <div>
                <input type="email" name="email" />
            </div>
            <div>
                <input type="password" name="pass" />
            </div>
            <div>
                <input type="submit"/>
            </div>
        </form>
    </body>
</html>
