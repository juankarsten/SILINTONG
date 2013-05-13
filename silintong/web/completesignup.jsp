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
        
        <h1>Complete Your Profile</h1>
        <form action="SignUpCompleted" method="post" enctype="multipart/form-data">
            
            <div>
                First Name: <input type="text" name="firstname" placeholder="first name" />
                Last Name: <input type="text" name="lastname" placeholder="last name" />
            </div>
            <div>
                Username: <input type="text" name="username"  placeholder="username" />
            </div>
            <div>
                Password: <input type="password" name="pass" placeholder="password" />
            </div>
            <div>
                Retype Password: <input type="password" name="pass2" placeholder="retype password" />
            </div>
            <div>
                Email: <input type="email" name="email" placeholder="email" />
            </div>
            <div>
                <div>
                    Sex:
                </div>
                <div>
                    <input type="radio" name="sex" value="male" checked/>Male
                    <input type="radio" name="sex" value="female"/>Female
                </div>
            </div>
            <div>
                Birthday: 
                <select name="datebday">
                    <%
                        for (int ii=1; ii<=31;ii++){
                            out.print("<option>"+ii+"</option>");
                        }
                    %>
                </select>
                <select name="monthbday">
                    <%
                        String[] months={
                            "January","February","March","April","May","June",
                            "July","August","September","October","November","December"
                        };
                        for (int ii=0; ii<months.length;ii++){
                            out.print("<option>"+months[ii]+"</option>");
                        }
                    %>
                </select>
                <select name="yearbday">
                    <%
                        for (int ii=1900; ii<=2013;ii++){
                            out.print("<option>"+ii+"</option>");
                        }
                    %>
                </select>
            </div>
                <div>
                    <div><h4>Choose Photo</h4></div>
                    <div><input type="file" name="foto" /></div>
                </div>
            <div>
                <input type="submit" value="sign up"/>
            </div>
        </form>
    </body>
</html>
