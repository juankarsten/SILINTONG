<%-- 
    Document   : postquestion
    Created on : May 13, 2013, 3:08:04 PM
    Author     : GG
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
            Enumeration usersession = session.getAttributeNames();
            if(usersession == null) {
                response.sendRedirect("index.jsp");
            }
%>
<html>
    <head>
        <link rel="stylesheet" href="css/foundation.css" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Post Pertanyaan Baru</title>
        <script src="js/jquery-1.9.1.js"></script>
        <script src="js/jquery-ui-1.10.3.custom.min.js"></script>
    </head>
    <body>
        <script>

 
            $(function() {
                $( "#datepicker" ).datepicker({
                    dateFormat : 'yy/mm/dd',
                    changeMonth : true,
                    changeYear : true,
                    yearRange: '-100y:c+nn',
                    minDate: '+2d',
                    hideIfNoPrevNext: true
                });
             });
        </script>
         <div class="row">
            <div class='large-12 columns'>
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
                    <p><a href="editprofile.jsp" class="button">Edit Profile</a></p>	
		</div>
                <hr />
            </div>
         </div>
        <div class="row">
            <div class="large-9 columns">
                <legend>Buat Pertanyaan Baru</legend>
                <hr/>
                    <div class="small-9 columns">
                    
                    </div>
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
