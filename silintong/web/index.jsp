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
		<div class="large-12 columns">
			<h1>SILINGTONG</h1>
			<p id="subname">Sistem Informasi Saling Tolong</p>

			<hr />
		</div>
	</div>

	<div class="row">
		<div class="large-8 columns">
			<h3>Welcome!</h3>

			<!-- Grid Example -->
			
			<div class="row">
				<form>
				  <fieldset class="large-10 columns">
					<legend>Login</legend>

					<div class="row">
					  <div class="small-9 large-10 columns">
						<label>Username</label>
						<input type="text" placeholder="Username">
						<label>Password</label>
						<input type="password" placeholder="Password">
						<a href="#" class="button">Login</a>
					  </div>
					  
					</div>

				  </fieldset>
				</form>
			</div>
			
			<div class="row">
				
			</div>


      <div class="row">
       
      </div>
		</div>

		<div class="large-4 columns">
			<h4>Getting Started</h4>
			<p>We're stoked you want to try Foundation! To get going, this file (index.html) includes some basic styles you can modify, play around with, or totally destroy to get going.</p>

			<h4>Other Resources</h4>
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
