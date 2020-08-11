<%@ page import="ge.edu.freeuni.api.model.question.Question" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
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
        .bgimg {
            text-align: center;
        }
    </style>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topright w3-container w3-xlarge">
        <p>
            <button class="w3-button w3-black"><a href="/userhomepage">Home Page</a></button>
        </p>
        <p>
            <button class="w3-button w3-black"><a href="/">Log Out</a></button>
        </p>
    </div>
    <form action="${pageContext.request.contextPath}/questionsWrapper/<%=choice%>/<%=quizId%>" method="post">
        <%

            String q = question.getQuestion();
            out.print("<p style=\"font-size:50px\"> Question #" + (choice + 1) + ", type: " + question.getType() + ":</p>");
            out.print("<p style=\"font-size:30px\">Question: <b>\"" + q + "\"</b></p>");
            List<String> answers = question.getChoices();
            List<Character> chars = new ArrayList<>();
            char ch = 'a';
            int id = 0;
            out.print("<h2> Fill In The Blank: </h2><br>");
            out.print("<br>");
            out.print("<br>");
        %>

        <input type="text" name="correctAnswer" required placeholder="Enter correct answer" id="correctAnswer"/>
        <input type="submit" value="Submit Answer" id="submitButton">
    </form>
</div>
</body>
</html>

