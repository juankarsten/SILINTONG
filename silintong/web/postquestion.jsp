<%-- 
    Document   : postquestion
    Created on : May 13, 2013, 3:08:04 PM
    Author     : GG
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
                <h1>SILINGTONG</h1>
                <p id="subname">Sistem Informasi Saling Tolong</p>
                <hr />
            </div>
         </div>
        <div class="row">
            <div class="large-9 columns">
                <legend>Buat Pertanyaan Baru</legend>
                <hr/>
                    <div class="small-9 columns">
                    <form action="NewQuestionController" method="post">
                        <label for="judul">Judul Pertanyaan:</label>
                        <input type="text" id="judul" placeholder="Judul" name="judul"/>
                        <label for="isipertanyaan">Isi Pertanyaan:</label>
                        <input type="text" id="isipertanyaan" placeholder="Isi" name="isipertanyaan"/>
                        <label for="datepicker">Deadline:</label>
                        <input type="text" id="datepicker" placeholder="Deadline" name="deadline"/>
                        <label for=poin">Poin yang akan diberikan:</label>
                        <br/>
                        <input type="radio" name="poin" value="10"> 10<br/>
                        <input type="radio" name="poin" value="20"> 20<br/>
                        <input type="radio" name="poin" value="50"> 50<br/>
                        <input type="radio" name="poin" value="100"> 100
                        <br/>
                        <label for=poin">Kategori Pertanyaan:</label>
                        <br/>
                        <select name="kategori">
                            <option value="Umum">Umum</option> 
                            <option value="Edukasi">Edukasi</option> 
                            <option value="Hiburan">Hiburan</option> 
                            <option value="Paybro">PayBro</option> 
                            <option value="Rakoon">Rakoon</option> 
                        </select>
                        <br/>
                        <label for="filetambahan">Upload File(Optional):</label>
                        <br/>
                        <input type="file" id="filetambahan" placeholder="Upload File" name="filetambahan"/>
                        <input type ='submit' value ='Buat Pertanyaan' class='button'/>
                    </form>
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
