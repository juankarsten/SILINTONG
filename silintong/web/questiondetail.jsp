<%-- 
    Document   : questiondetail
    Created on : May 14, 2013, 8:46:28 PM
    Author     : GG
--%>

<%@page import="com.silintong.db.DBConnector"%>
<%@page import="com.silintong.model.Rating"%>
<%@page import="com.silintong.model.Answer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Enumeration usersession = session.getAttributeNames();
        if (usersession == null) {
            response.sendRedirect("index.jsp");
        }
    %>
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
                <h3>Hello, 
                    <%
                        String username = usersession.nextElement().toString();
                        out.print(username);
                    %>!</h3>
                <p><a href="editprofile.jsp" class="button">Edit Profile</a></p>		
                <a href="Logout" class='alert button tiny round right'>
                    Logout
                </a>

            </div>
            <hr />
        </div>

        <div class="row">
            <div class="small-9 columns">
                <legend><h3>Detail Pertanyaan:</h3></legend>
                <hr/>
                <%
                    ArrayList<String> QuestionDetail = (ArrayList<String>) request.getAttribute("questiondetail");
                    out.println("<label>Judul Pertanyaan: </label>" + QuestionDetail.get(0));
                    out.print("<br/>");
                    out.print("<br/>");
                    out.println("<label>Yang Bertanya: </label>" + QuestionDetail.get(1));
                    out.print("<br/>");
                    out.print("<br/>");
                    out.println("<label>Isi Pertanyaan: </label>" + QuestionDetail.get(2));
                    out.print("<br/>");
                    out.print("<br/>");
                    out.println("<label>Deadline: </label>" + QuestionDetail.get(3));
                    out.print("<br/>");
                    out.print("<br/>");
                    out.println("<label>Kategori: </label>" + QuestionDetail.get(4));
                    out.print("<br/>");
                    out.print("<br/>");
                    out.print("<label>Poin: </label>" + QuestionDetail.get(5));
                %>
                <hr/>

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
                        <hr/>
		</div>
        </div>
        <%
                    ArrayList<Answer> answers = (ArrayList<Answer>)request.getAttribute("answers");
                    if(answers != null){
                        if(!answers.isEmpty()){
                             for(int cnt=0;cnt<answers.size();cnt++){
                                 out.print("<div class='row'>");
                                 out.print("<div class='large-3 columns'>");
                                 out.print("<h4>");
                                 out.print(answers.get(cnt).getIdusername());
                                 out.print("</h4>");
                                 out.print("<p>");
                                 DBConnector db = new DBConnector();
                                 String idanswer = answers.get(cnt).getIdanswer();
                                 int totalrating = 0;
                                 if(db.getAnswerRating(idanswer)){
                                     totalrating = db.getRating(idanswer);
                                 }
                                 out.print("Total Rating: "+totalrating);
                                 out.print("</p>");
                                 out.print("</div>");
                                 out.print("<div class='large-7 columns'>");
                                 if(answers.get(cnt).getIsapproved().equals("true")){
                                     out.print("<img src='img/checked.png' />");
                                 }
                                 out.print(answers.get(cnt).getContent());
                                 if (username.equals(QuestionDetail.get(1))) {
                                    out.print("<form action='ApproveAnswer' method='post' id='approveans'>");
                                    out.print("<input type='hidden' name='username' value='"+username+"'>");
                                    out.print("<input type='hidden' name='userp' value='"+answers.get(cnt).getIdusername()+"'>"); 
                                    out.print("<input type='hidden' name='poin' value='"+QuestionDetail.get(5)+"'>"); 
                                    out.print("<input type='hidden' name='answerid' value='"+answers.get(cnt).getIdanswer() +"'>");                      
                                    out.print("<input type = 'submit' class='button small success' value ='Approve this answer'>");
                                    out.print("</form>");
                                }
                                 out.print("</div>");
                                 out.print("<div class='large-2 columns'>");
                                 boolean ratenow[]=new boolean[5];
//                                 int rate=Integer.parseInt() -1;
                                 String temp=Rating.getRate(answers.get(cnt).getIdusername(), answers.get(cnt).getIdanswer());
//                                 out.print(temp+"aa"+answers.get(cnt).getIdusername());
//                                 out.print(rate);
                                 %>
                                 <form action="rate.jsp">
                                     <select name="rate"  >
                                         <option >1</option>
                                         <option >2</option>
                                         <option >3</option>
                                         <option >4</option>
                                         <option >5</option>
                                     </select>
                                     <input type="hidden" name="ans" value="<%=answers.get(cnt).getIdanswer() %>" />
                                     <input type="hidden" name="user" value="<%=username %>" />
                                     <input type="submit" value="Rate Answer" class="button small"/>
                                 </form>
                                 <%
                                 
                                 out.print("</div>");
                                 out.print("<hr/>");
                                 out.print("</div>");                            
                             }
                         }
                    }
               
        %>

        <div class='row'>
            <div class="large-4 columns"></div>
            <div class="large-4 columns">
                <form method="post" action='PostAnswer'>
                    <input type='hidden' value='<% out.print(QuestionDetail.get(6));%>' name='idquestion'/>
                    <input type='submit'class='button small' value ='Answer this Post'>
                </form>
            </div>    
            <div class="large-4 columns"></div>
        </div>

        <script>
            document.write('<script src=' +
                    ('__proto__' in {} ? 'js/zepto' : 'js/jquery') +
                    '.js><\/script>')
        </script>
        <script src="js/foundation.min.js"></script>
        <script>
            $(document).foundation();
        </script>
    </body>
</html>
