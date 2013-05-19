<%-- 
    Document   : editprofile
    Created on : May 14, 2013, 4:22:27 PM
    Author     : juan.karsten
--%>

<%@page import="java.io.File"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.silintong.model.User"%>
<%@page import="com.silintong.extra.Validator"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.silintong.db.DBConnector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
              <link rel="stylesheet" href="css/foundation.css" />
  
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Enumeration usersession = session.getAttributeNames();
            if(usersession == null) {
                response.sendRedirect("index.jsp");
            }
//            String username=(String)session.getAttribute("username");
            String username = usersession.nextElement().toString();
            if(!Validator.isExist(username)){
                response.sendRedirect("index.jsp");
            }
            User user=new DBConnector().searchUsername(username);
         %>
         <div class="row">
		<div class="large-9 columns">
			<h2>SILINTONG</h2>
			<p>Sistem Informasi Saling Tolong</p>

		</div>
                <div class="large-3 columns">

                    <h3>Hello, 
                   <%
                        out.print(username);
                    %>!</h3>
                    <a href="Logout" class='alert button tiny round right'>
                        Logout
                    </a>
		</div>
                <hr />
	</div>
         <div class="row">
             <div class="small-11 columns">
         <fieldset>
                 <legend>Update Profile</legend>
         <form action="editprofile" method="post" enctype="multipart/form-data">
             <input type="hidden" name="username" value=<%=user.getUsername()%> />
             <input type="hidden" name="id" value=<%=user.getId() %> />
             <input type="hidden" name="poin" value=<%=user.getPoint() %> />
             <input type="hidden" name="foto2" value=<%=user.getFoto() %> />
                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">First name: </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="text"  id="right-label" placeholder="First name" name="fname" value=<%= user.getFName() %> />
                        </div>
                      </div>
                   </div>
                </div>


                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Last name: </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="text"  id="right-label" placeholder="Lname" name="lname" value="<%=user.getLname()%>" />
                        </div>
                      </div>
                   </div>
                </div>

                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Password: </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="password"  id="right-label" placeholder="Password" name="pass" value="<%= user.getPass() %>" />
                        </div>
                      </div>
                   </div>
                </div>

                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Birthday </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="text"  id="right-label" placeholder="birthday" name="bday" value=<%= user.getBday() %> />
                        </div>
                      </div>
                   </div>
                </div>

              <%
                        boolean jk[]=new boolean[2];
                        if(user.getSex().equals("male")){
                            jk[0]=true;
                        }else{
                            jk[1]=true;
                        }
               %>

                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Male: </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="radio"  id="right-label" name="sex" value="male" checked=<%= jk[0] %> />
                        </div>
                      </div>
                   </div>
                </div>

                        <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Female: </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="radio"  id="right-label" name="sex" value="female" checked=<%= jk[1] %> />
                        </div>
                      </div>
                   </div>
                </div>


                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Foto User: </label>
                         </div>

                         <div class="small-5 columns">
                                 <% if(Validator.isExist(user.getFoto())){%>
                                 <img src="ReadImage?filename=<%=user.getFoto()%>" />
                                <%}%>
                                <input type="file" name="foto" />

                        </div>
                      </div>
                   </div>
                </div>             




                <div class="row">
                   <div class="small-8 columns">
                      <div class="row">
                         <div class="small-2 columns">
                            <label for="right-label" class="left inline">Email: </label>
                         </div>

                         <div class="small-5 columns">
                             <input type="text"  id="right-label" placeholder="email" name="email" value=<%= user.getEmail() %> />
                        </div>
                      </div>
                   </div>
                </div>

                <div class="row">
                    <div class="small-8 columns">
                       <input for="right-label" class="left button" type="submit" value="update"/>
                    </div>
                </div>
             
         </form>
       </fieldset>
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
