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
                        <form action = "LoginController" method = "post" >                 
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
				<form>
				  <fieldset class="large-8 columns">
					<legend>Sign Up</legend>
                                        <img src='img/we-need-you.jpg' alt ='weneedyou'/>
				  </fieldset>
				</form>
                            <div class='large-4 columns'>
                                <h3>Sign-Up here to start helping others</h3>
                                <button href='#'>Sign Up</button>
                            </div>
			</div>
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
