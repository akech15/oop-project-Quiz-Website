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
        .bgimg{
            text-align: center;
        }
    </style>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <form
          action="${pageContext.request.contextPath}/questionsWrapper/<%=choice%>/<%=quizId%>" method="post">
        <%
            String q = question.getQuestion();
            out.print("<p style=\"font-size:50px\"> Question #: " + choice + ", type: " + question.getType() + ":</p>");
            out.print("<p style=\"font-size:30px\">Question: <b>\"" + q + "\"</b></p>");
            List<String> answers = question.getChoices();
            List<Character> chars = new ArrayList<>();
            char ch = 'a';
            int id = 0;

            out.print("<h2> Possible Answers: </h2>");
            for (int i = 0; i < answers.size(); i++, ch++, id++) {
                out.print("<h2> " + ch + ": <b>"  + answers.get(i) + "</b></h2>");
                chars.add(ch);
            }

            String toShow = "<label>Enter if correct answer is either ";
            for (int i = 0; i < chars.size() - 1; i++) {
                toShow += chars.get(i) + ", ";
            }
            toShow += "or " + chars.get(chars.size() - 1);
            toShow += "</label>";
            out.print(toShow);
            out.print("<br>");
        %>

        <input type="text" name="correctAnswer" required placeholder="Enter correct answer" id="correctAnswer"/>
        <input type="submit" value="Submit Answer" id="submitButton">

    </form>
</div>
</body>
</html>

