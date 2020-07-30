<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/preQuiz.css"/>
</head>
<body>

<h2 class="glow">ARE YOU READY FOR THE QUIZ?</h2>
   <div class="a">
       <%
           Long quiz_id = (Long) request.getAttribute("quiz_id");
           Integer index = (Integer)request.getAttribute("index");
           out.print(String.format("<p><button class=\"w3-button w3-black\"><a  href=\"/playTrueFalse/%d/%d\">HELL YEAH</a></button></p>", quiz_id, index));
       %>
   </div>

</body>
</html>
