<%-- 
    Document   : kategoriumum
    Created on : May 14, 2013, 6:03:05 PM
    Author     : GG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                            <h3>Hello, [User]!</h3>
                            <p>Edit Profile</p>
			
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
                                out.print("<form>");
                                out.print("<input class='small button' type='submit' value='Details'>");
                                out.print("</form>");
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
