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
        <link rel="stylesheet" href="css/foundation.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SILINGTONG</title>

    </head>
    <body>
        <div id ='content'>
            <div class="row">
                <div class='large-12 columns'>
                    <div class="large-8 columns">
                        <h1>SILINTONG</h1>
                        <p id="subname">Sistem Informasi Saling Tolong</p>
                    </div>
                    <div class ='large-4 columns'>
                        <br />
                        <div id='loginbox'>
                            <form action = "home" method = "post">                 
                                <div class="small-3 columns">
                                    <label for="right-label" class="right inline">Username</label>
                                    <label for="right-label" class="right inline">Password</label>
                                </div>

                                <div class="small-9 columns">
                                    <input type="text" id="right-label" placeholder="Username" name="namauser"/>
                                    <input type="password" id="right-label" placeholder="Password" name="katasandi"/>
                                    <input type ="submit" value ="Login" class="button right"/>
                                </div>
                            </form>
                        </div>
                    </div>
                    <hr />
                </div>
            </div>
            <div class="row">
                <div class="large-12 columns">
                    <div class="row">
                        <fieldset class="large-8 columns">
                            <legend>Sign Up</legend>
                            <div class="orbit-container">
                                <ul data-orbit="" class="orbit-slides-container">
                                    <li class="active">
                                        <img src="img/we-need-you.jpg">
                                        <div class="orbit-caption">We need you to start helping others here</div>
                                    </li>
                                    <li>
                                        <img src="img/helphand.jpg">
                                        <div class="orbit-caption">Helping others is fun!</div>
                                    </li>
                                    <li>
                                        <img src="img/morning.jpg">
                                        <div class="orbit-caption">Let's Help Others Everyday!</div>
                                    </li>
                                </ul>
                            </div>
                        </fieldset>
                        <div class="large-4 columns">
                            <h3>Sign up here to start helping others</h3>
                            <a href="completesignup.jsp"><button>Sign Up</button></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/jquery-1.9.1.js"></script>
        <script src="js/zepto.js"></script>
        <script src="js/foundation.min.js"></script>
        <script src="js/foundation.orbit.js"></script>  
        <script>
            $(document).foundation();
        </script>  
    </body>
</html>
