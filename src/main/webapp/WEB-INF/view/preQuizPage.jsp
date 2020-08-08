<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/preQuiz.css"/>
</head>
<body>

<h2 class="glow">ARE YOU READY FOR THE QUIZ?</h2>
   <div class="a">
       <%
           Long quizId = (Long) request.getAttribute("quizId");
           out.print(String.format("<a  style=\"font-size : 40px\" " +
                   "href=\"/startPlayingQuiz/%d\">HELL YEAH</a><br><br>", quizId));

           out.print("<a  style=\"font-size : 40px\" " +
                   "href=\"/userhomepage\">ABSOLUTELY NOT :( </a>");
       %>
   </div>

</body>
</html>
