<%-- 
    Document   : transfer
    Created on : May 15, 2013, 6:11:51 AM
    Author     : juan.karsten
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
        <title>Transfer Poin</title>
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
                <legend>Transfer Poin</legend>
                <hr/>
                    <div class="small-9 columns">
                        <form action ='TransferController'>
                            <label for="usertujuan">Poin akan ditransfer ke :</label>
                            <input type ='text' id='usertujuan' name='usertujuan'/>
                            <label for=poin">Poin yang akan ditransfer:</label>
                            <br/>
                            <input type="radio" name="poin" value="10"> 10<br/>
                            <input type="radio" name="poin" value="20"> 20<br/>
                            <input type="radio" name="poin" value="50"> 50<br/>
                            <input type="radio" name="poin" value="100"> 100<br/><br/><br/>
                            <input type='hidden' name='userasal' value='<%=username%>'/>
                            <input type='submit' class='button' value='Lakukan Transfer Poin'/>
                        </form>
                    </div>
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
