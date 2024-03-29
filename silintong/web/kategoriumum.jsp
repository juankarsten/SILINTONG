<%-- 
    Document   : kategoriumum
    Created on : May 14, 2013, 6:03:05 PM
    Author     : GG
--%>

<%@page import="com.silintong.model.Question"%>
<%@page import="java.util.ArrayList"%>
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
                        <li><a href="latestQuestions">Latest Questions</a></li>
                        <li class="divider"></li>
                        <li>
                      <form id="form1" action="myquestions" method="post">
                          <li><a href="javascript:;" onclick="document.getElementById('form1').submit();">My Questions</a></li>
                          <input type="hidden" name="username" value="<% out.print(username); %>"/>
                      </form>
                  </li>  
                        <li class="divider"></li>
                        <li class="has-dropdown active"><a href="#">All Categories</a>
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
                <legend><h3>Kategori Umum</h3></legend>
                <hr/>
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
                <a  href="postquestion.jsp" ><button class='small'>Post Now!</button></a>
                <h4>Leaderboards</h4>
                <jsp:include page="leaderboard.jsp" />
                <h4>Beli Poin</h4>
                <p>Untuk para Silintongers yang ingin membeli poin, dapat membeli via:</p>
                 <form id="form9" action="buyPoints" method="post">
                          <a href="javascript:;" onclick="document.getElementById('form9').submit();"><img src="img/paybro.png" alt='paybro'></a></li>
                          <input type="hidden" name="username1" value="<% out.print(username); %>"/>
                       </form>
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
