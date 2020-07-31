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
           out.print(String.format("<p><button class=\"w3-button w3-black\"><a  href=\"/startPlayingQuiz/%d\">HELL YEAH</a></button></p>", quizId));
       %>
   </div>

</body>
</html>
