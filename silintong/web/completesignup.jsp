<%-- 
    Document   : completesignup
    Created on : May 12, 2013, 7:47:53 PM
    Author     : juan.karsten
--%>

<%@page import="com.silintong.extra.Validator"%>
<%@page import="com.silintong.model.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Complete Your Profile</title>
        <link rel="stylesheet" href="css/foundation.css" />
        <script src="js/vendor/custom.modernizr.js"></script>
    </head>
    <body>
        <%
            int geterror=0;
            String temp=request.getParameter("error");
            if(Validator.isExist(temp)){
                try{
                    geterror=Integer.parseInt(temp);
                }catch(Exception e){
                    
                }
            }
            
        %>
        
        <div class="row">
		<div class="small-9 columns">
			<h2>SiLINTONG</h2>
			<p>Sistem Informasi Saling Tolong</p>

		</div>
            <hr/>
	</div>
        <div class="row">
            <div class="small-9 columns">
            <form action="SignUpCompleted" method="post" enctype="multipart/form-data">
                <div class="small-12 columns ">
                    <div class="small-4 columns">
                        First Name:
                    </div>
                    <div class="small-8 columns left">
                             <input type="text"  id="right-label" placeholder="First name" name="firstname"  />
                            <%
                            if((geterror&1)!=0){
                                out.print("complete First Name");
                            }
                            %>
                    </div>
                </div>
                  
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        Last Name:
                    </div>
                    <div class="small-8 columns">
                             <input type="text"  id="right-label" placeholder="Last name" name="lastname"  />
                             <%
                                if((geterror&16)!=0){
                                    out.print("complete Last Name");
                                }
                            %>
                    </div>
                </div>
                   
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        User Name:
                    </div>
                    <div class="small-8 columns">    
                        
                             <input type="text"  id="right-label" placeholder="User name" name="username"  />
                             <%
                                if((geterror&16)!=0){
                                    out.print("complete User Name");
                                }
                            %>
                    </div>
                </div>
                        
                    
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        Password:
                    </div>
                    <div class="small-8 columns">    
                        <input type="password" name="pass" placeholder="password" />
                        <%
                            if((geterror&4)!=0){
                                out.print("complete Password");
                            }
                        %>
                    </div>
                </div>
                    
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        Retype Password:
                    </div>
                    <div class="small-8 columns">    
                        <input type="password" name="pass2" placeholder="retype password" />
                        <%
                            if((geterror&8)!=0){
                                out.print("please retype password again");
                            }
                        %>
                    </div>
                </div>
                    
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        Email:
                    </div>
                    <div class="small-8 columns">    
                        <input type="email" name="email" placeholder="email" />
                        <%
                            if((geterror&2)!=0){
                                out.print("complete your email");
                            }
                        %>
                    </div>
                </div>
                  
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        Sex:
                    </div>
                    <div class="small-8 columns">    
                        <div class="small-4  columns">
                             <input type="radio" name="sex" value="male" checked/>Male
                        </div>
                        <div class="small-4 columns">
                             <input type="radio" name="sex" value="female"/>Female
                        </div>
                    </div>
                </div>
                    
                    
                <div class="small-12 columns">
                    <div class="small-4 columns">
                        Birthday:
                    </div>
                    <div class="small-8 columns">
                    <div class="small-3 columns small-offset-1">
                        <select name="datebday" >
                        <%
                            for (int ii=1; ii<=31;ii++){
                                out.print("<option>"+ii+"</option>");
                            }
                        %>
                    </select>
                    </div>
                    <div class="small-3 columns small-offset-1">
                    <select name="monthbday" >
                        <%
                            String[] months={
                                "January","February","March","April","May","June",
                                "July","August","September","October","November","December"
                            };
                            for (int ii=0; ii<months.length;ii++){
                                out.print("<option>"+months[ii]+"</option>");
                            }
                        %>
                    </select>
                    </div>
                    <div class="small-3 columns small-offset-1">
                    <select name="yearbday" >
                        <%
                            for (int ii=1900; ii<=2013;ii++){
                                out.print("<option>"+ii+"</option>");
                            }
                        %>
                    </select>
                    </div>
                    </div>
                </div>
                    
                    <div>
                        <div><h4>Choose Photo</h4></div>
                        <div><input type="file" name="foto" /></div>
                    </div>
                <div>
                    <input class="button" type="submit" value="sign up"/>
                </div>
            </form>
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
