<%-- 
    Document   : index
    Created on : May 11, 2013, 12:58:46 PM
    Author     : dwi.novanto
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.silintong.model.Question"%>
<%@page import="com.silintong.extra.Validator"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<%
            Enumeration usersession = session.getAttributeNames();
%>
    <head>
        <link rel="stylesheet" href="css/foundation.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SILINGTONG</title>
    </head>
    <body>
         <div class="row">
		<div class="large-9 columns">
                    <h2>SILINTONG</h2>
                    <p id="subname">Sistem Informasi Saling Tolong</p>
		</div>
                <div class="large-3 columns">
                    <h3>Hello, 
                   <%
                        String username = usersession.nextElement().toString(); 
                        out.print(username);
                    %>!</h3>
                    <p>Edit Profile!</p>	
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
            <%
               ArrayList<Question> listOfQst = (ArrayList<Question>)request.getAttribute("latestQuestion");
               if(!listOfQst.isEmpty()){
                    out.print("<table>");
                    out.print("<thead>");
                        out.print("<tr>");
                            out.print("<td>");
                            out.print("Category");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Title");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Content");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Posted");
                            out.print("</td>");
                            out.print("<td>");
                            out.print("Duedate");
                            out.print("</td>");
                        out.print("</tr>");    
                    out.print("</thead>");
                    
                    out.print("<tbody>");
                        for(int cnt=0;cnt<listOfQst.size();cnt++){
                            out.print("<tr>");
                                out.print("<td>");  
                                out.print(listOfQst.get(0).getIdcategories());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(0).getTitle());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(0).getContent());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(0).getDateposted());
                                out.print("</td>");
                                out.print("<td>");  
                                out.print(listOfQst.get(0).getDuedate());
                                out.print("</td>");
                            out.print("</tr>");
                        } 
                    out.print("</tbody>");
                out.print("</table>");
               }
                 else {
                   out.print("No Questions Posted yet.");
                }
               
            %>
         
            </div>
		<div class="large-3 columns">
			<h5>Post A New Question</h5>
			<p>We're stoked you want to try Foundation! To get going, this file (index.html) includes some basic styles you can modify, play around with, or totally destroy to get going.</p>

			<h4>Leaderboards</h4>
			<p>Once you've exhausted the fun in this document, you should check out:</p>
			<ul class="disc">
				<li><a href="http://foundation.zurb.com/docs">Foundation Documentation</a><br />Everything you need to know about using the framework.</li>
				<li><a href="http://github.com/zurb/foundation">Foundation on Github</a><br />Latest code, issue reports, feature requests and more.</li>
				<li><a href="http://twitter.com/foundationzurb">@foundationzurb</a><br />Ping us on Twitter if you have questions. If you build something with this we'd love to see it (and send you a totally boss sticker).</li>
			</ul>
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
