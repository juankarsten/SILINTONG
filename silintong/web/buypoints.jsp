<%-- 
    Document   : buypoints
    Created on : May 13, 2013, 11:11:04 AM
    Author     : GG
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.silintong.model.Question"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--[if IE 8]> 				 <html class="no-js lt-ie9" lang="en" > <![endif]-->
<html class="no-js" lang="en" > <!--<![endif]-->
    <%
        Enumeration usersession = session.getAttributeNames();
        if (usersession == null) {
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
                <h2>SILINTONG</h2>
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
                  
                  <li><a href="latestQuestions">Latest Questions</a></li>
                  <li class="divider"></li>
                  <li>
                      <form id="form1" action="myquestions" method="post">
                          <li><a href="javascript:;" onclick="document.getElementById('form1').submit();">My Questions</a></li>
                          <input type="hidden" name="username" value="<% out.print(username); %>"/>
                      </form>
                  </li>  
                  <li class="divider"></li>
                  <li class="has-dropdown"><a href="#">All Categories</a>
                  <ul class="dropdown">
                      <li><a href="EducationCategory">Education</a>
                      <li><a href="EntertainmentCategory">Entertainment</a>
                      <li><a href="GeneralCategory">General</a>
                      <li><a href="PayBroCategory">PayBro</a>
                      <li><a href="RakoonCategory">Rakoon</a>
                  </ul>

                <li class="divider"></li>
                </ul>
                <ul class="right">
                        <li class="has-form">
                    <form action="search.jsp" method="get">
                      <div class="row collapse">
                        <div class="small-8 columns">
                          <input type="text" name="search">
                        </div>
                        <div class="small-4 columns">
                            <input type="submit" class="alert button" value="search">
                        </div>
                      </div>
                    </form>
                            
                </ul>
              </section>
            </nav>

            <div class="small-9 columns">
                <h2>Buy Points</h2>
                <h4>Powered by PayBro</h4>
                <hr/>
                   <form action="buyPointsPost" method="post">
                       <p>BuyPoints! merupakan sarana pembelian poin dengan menggunakan layanan bantuan dari
                    PayBro selaku client dari web kami. Untuk keperluan pembayaran, silahkan cantumkan pesan
                    pembayaran Anda pada message untuk diverifikasi oleh PayBro.<br/>Selamat bertransaksi!
                       </P>
                       <hr/>
                       <p>
                           Poin Anda saat ini : <% out.print(request.getAttribute("userpoint1"));%>  
                       </p>
                       <p>
                           Harga : <br/>
                           10 Points  : IDR 30.000<br/>
                           20 Points  : IDR 50.000<br/>
                           50 Points  : IDR 130.000<br/>
                           100 Points : IDR 240.000<br/><br/>
                           Amount of Points:<br/></p>
                    <input type="radio" name="poin" value="10"> 10<br/>
                        <input type="radio" name="poin" value="20"> 20<br/>
                        <input type="radio" name="poin" value="50"> 50<br/>
                        <input type="radio" name="poin" value="100"> 100
                        <br/>
                        <br/>
                        <textarea id="answercontent" placeholder="Write your payment message here." name="answercontent"/></textarea>
                        <input type ='submit' value ='Buy Points!' class='button left small'/>
                        <input type ='hidden' name='username' value ='<% out.print(username);%>'/>
                   </form>

            </div>

            <div class="large-3 columns">
                <h5>Post A New Question</h5>
                <br/>
                <a  href="postquestion.jsp" ><button class='small'>Post Now!</button></a>
                <h4>Leaderboards</h4>
                <jsp:include page="leaderboard.jsp" />
                <h4>Transfer Poin</h4>
                <a href="transfer.jsp" class="button small">Transfer</a>
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
  <!--
  
  <script src="js/foundation/foundation.js"></script>
  
  <script src="js/foundation/foundation.dropdown.js"></script>
  
  <script src="js/foundation/foundation.placeholder.js"></script>
  
  <script src="js/foundation/foundation.forms.js"></script>
  
  <script src="js/foundation/foundation.alerts.js"></script>
  
  <script src="js/foundation/foundation.magellan.js"></script>
  
  <script src="js/foundation/foundation.reveal.js"></script>
  
  <script src="js/foundation/foundation.tooltips.js"></script>
  
  <script src="js/foundation/foundation.clearing.js"></script>
  
  <script src="js/foundation/foundation.cookie.js"></script>
  
  <script src="js/foundation/foundation.joyride.js"></script>
  
  <script src="js/foundation/foundation.orbit.js"></script>
  
  <script src="js/foundation/foundation.section.js"></script>
  
  <script src="js/foundation/foundation.topbar.js"></script>
  
  -->
  
  <script>
    $(document).foundation();
  </script>
</body>

</html>

