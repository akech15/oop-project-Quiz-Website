<%@ page import="ge.edu.freeuni.api.model.question.Question" %>
<%@ page import="ge.edu.freeuni.server.services.question.QuestionService" %>
<%@ page import="org.springframework.beans.factory.annotation.Autowired" %>
<%@ page import="ge.edu.freeuni.server.repository.question.QuestionRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoice.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoiceSubmitted.css"/>
    <title>Play Quiz</title>

    <%
        Question question = (Question) request.getAttribute("question");
        Long choice = (Long) request.getAttribute("index");
        Long quizId = (Long) request.getAttribute("quizId");
    %>

    <style>
        .bgimg{
            text-align: center;
        }
    </style>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home Page</a></button></p>
        <p><button class="w3-button w3-black"><a  href="/">Log Out</a></button></p>
    </div>

    <form action="${pageContext.request.contextPath}/questionsWrapper/<%=choice%>/<%=quizId%>" method="post">
        <%
            String q = question.getQuestion();
            out.print("<p style=\"font-size:50px\"> Question #" + (choice +1) + ", type: " + question.getType() + ":</p>");
            out.print("<p style=\"font-size:30px\">Question: <b>\"" + q + "\"</b></p>");
        %>
         True <input type="checkbox" name = "trueCheckBox" id="trueCheckBox">
         False <input type="checkbox" id="falseCheckBox"><br>
        <input type="submit" value = "Submit Answer" id="submitButton">
    </form>
</div>

<script>
    window.onclick = function(event) {

        window.onclick = function(event) {
            if (event.target == document.getElementById("trueCheckBox")){
                if(document.getElementById("falseCheckBox").checked = true){
                    uncheckFalse();
                }
            } else if (event.target == document.getElementById("falseCheckBox")){
                if(document.getElementById("trueCheckBox").checked = true){
                    uncheckTrue();
                }
            }
        }

        function uncheckTrue() {
            document.getElementById("trueCheckBox").checked = false;
        }

        function uncheckFalse() {
            document.getElementById("falseCheckBox").checked = false;
        }

    }



</script>

</body>
</html>

