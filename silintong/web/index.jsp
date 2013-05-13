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
        <div class="row">
            <div class='large-12 columns'>
		<div class="large-8 columns">
			<h1>SILINGTONG</h1>
			<p id="subname">Sistem Informasi Saling Tolong</p>
			<hr />  
		</div>
                <div class ='large-3 columns'>
                    <br>
                        <form action ="login.jsp">
                           <div class="small-3 columns">
                                <label for="right-label" class="right inline">Username</label>
                                <label for="right-label" class="right inline">Password</label>
                            </div>
                            <div class="small-9 columns">
                                <input type="text" id="right-label" placeholder="Inline Text Input" name='username'>
                                <input type="password" id="right-label" placeholder="Inline Text Input" name='password'>
                            </div>
                            <button class='small' type='submit'>Login</button>
                        </form>
                </div>
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
                                <h3>Please Sign-Up here to start helping others</h3>
                                <button href='#'>Sign Up</button>
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
