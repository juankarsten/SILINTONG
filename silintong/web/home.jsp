<%-- 
    Document   : mainpage
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
			<h2>SILINTONG</h2>
			<p>Sistem Informasi Saling Tolong</p>

		</div>
                <div class="large-3 columns">

                    <h3>Hello, 
                   <%
                        String username = usersession.nextElement().toString(); 
                        out.print(username);
                    %>!</h3>
                    <p><a href="editprofile.jsp" class="button">Edit Profile</p></a>	
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
                  <li>
                      <form id="form1" action="myquestions" method="post">
                          <li><a href="javascript:;" onclick="document.getElementById('form1').submit();">My Questions</a></li>
                          <input type="hidden" name="username" value="<% out.print(username); %>"/>
                      </form>
                  </li>  
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
            <%
               ArrayList<Question> listOfQst = (ArrayList<Question>)request.getAttribute("latestQuestion");
               if(listOfQst != null){
                   if(!listOfQst.isEmpty()){
                    out.print("<table>");
                    out.print("<thead>");
                        out.print("<tr>");
                            out.print("<td>");
                            out.print("Category");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Username");
                            out.print("</td>");
                            out.print("<td width='290'>");
                            out.print("Title");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Posted");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Duedate");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Points");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Action");
                            out.print("</td>");
                        out.print("</tr>");    
                    out.print("</thead>");
                    
                    out.print("<tbody>");
                        for(int cnt=0;cnt<listOfQst.size();cnt++){
                            out.print("<tr>");
                                out.print("<td>");  
                                out.print(listOfQst.get(cnt).getIdcategories());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(cnt).getUsername());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(cnt).getTitle());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(cnt).getDateposted());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(cnt).getDuedate());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(cnt).getPoint());
                                out.print("</td>");
                                out.print("<td>");
                                out.print("<br>");                                 
                                out.print("<form action='QuestionDetail' method ='post'>");
                                out.print("<input type='hidden' name='idpertanyaan' value='"+listOfQst.get(cnt).getIdQuestion()+"'>");
                                out.print("<input type='hidden' name='konten' value='"+listOfQst.get(cnt).getContent()+"'>");
                                out.print("<input type='hidden' name='namakategori' value='"+listOfQst.get(cnt).getIdcategories()+"'>");
                                out.print("<input type='hidden' name='userposter' value='"+listOfQst.get(cnt).getUsername()+"'>");
                                out.print("<input type='hidden' name='qtitle' value='"+listOfQst.get(cnt).getTitle()+"'>");
                                out.print("<input type='hidden' name='duedate' value='"+listOfQst.get(cnt).getDuedate()+"'>");
                                out.print("<input type='hidden' name='poin' value='"+listOfQst.get(cnt).getPoint()+"'>");
                                out.print("<input type='hidden' name='username' value='"+username+"'>");
                                out.print("<input class='small button' type='submit' value='Details'>");
                                out.print("<input type='hidden' name='username' value='"+username+"'/>");
                                out.print("</form>");
                                out.print("</td>");
                            out.print("</tr>");
                        } 
                    out.print("</tbody>");
                out.print("</table>");
               }
                 
           }else if(listOfQst == null){
                   out.print("No Questions Posted yet.");
                }

            %>
         
            </div>
            
		<div class="large-3 columns">
			<h5>Post A New Question</h5>
                        <br/>
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

