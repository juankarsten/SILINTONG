<%-- 
    Document   : kategorihiburan
    Created on : May 14, 2013, 8:13:53 PM
    Author     : GG
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<%
            Enumeration usersession = session.getAttributeNames();
            if(usersession == null) {
                response.sendRedirect("index.jsp");
            }
%>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width" />
        <title>SiLINTONG : Sistem Informasi Saling Tolong</title>


        <link rel="stylesheet" href="css/foundation.css" />


        <script src="js/vendor/custom.modernizr.js"></script>

    </head>
    <body>

        <div class="row">
            <div class="large-9 columns">
                <h2>SiLINTONG</h2>
                <p>Sistem Informasi Saling Tolong</p>

            </div>
            <div class="large-3 columns">
                <h3>Hello, 
                    <%
                        String username = usersession.nextElement().toString();
                        out.print(username);
                    %>!</h3>
                <p><a href="editprofile.jsp" class="button">Edit Profile</a></p>		
                <a href="Logout" class='alert button tiny round right'>
                    Logout
                </a>

            </div>
            <hr />
        </div>

        <div class="row">

            <nav class="top-bar">
                <section class="top-bar-section">
                    <!-- Left Nav Section -->
                    <ul class="left">
                        <li class="divider"></li>
                        <li class="active"><a href="#">Latest Questions</a></li>
                        <li class="divider"></li>
                        <li><a href="#">My Questions</a></li>
                        <li class="divider"></li>
                        <li><a href="#">All Categories</a></li>
                        <li class="divider"></li>
                    </ul>
                    <ul class="right">
                        <li class="has-form">
                            <form>
                                <div class="row collapse">
                                    <div class="small-8 columns">
                                        <input type="text">
                                    </div>
                                    <div class="small-4 columns">
                                        <a href="#" class="alert button">Search</a>
                                    </div>
                                </div>
                            </form>
                    </ul>
                </section>
            </nav>

            <div class="small-9 columns">
                <legend><h3>Kategori Hiburan</h3></legend>
                <hr/>
            </div>
            <div class="large-3 columns">
                <h5>Post A New Question</h5>
                <a  href="postquestion.jsp" ><button class='small'>Post Now!</button></a>
                <h4>Leaderboards</h4>
                <h4>Beli Poin</h4>
                <p>Untuk para Silintongers yang ingin membeli poin, dapat membeli via:</p>
                <img src="img/paybro.png" alt='paybro'>
            </div>
        </div>

        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'js/vendor/zepto' : 'js/vendor/jquery') +
                    '.js><\/script>')
        </script>
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>
