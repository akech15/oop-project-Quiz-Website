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
            out.print("<h1> Question: #" + choice + " : " + q + "</h1><br>");

            List<String> answers = question.getChoices();
            List<Character> chars = new ArrayList<>();
            char ch = 'a';
            int id = 0;
            out.print("<h2> Possible Answers: </h2><br>");
            for (int i = 0; i < answers.size(); i++, ch++, id++) {
                out.print("<h2> " + ch + ": " +  answers.get(i) + " </h2>");
                out.print("<br>");
                chars.add(ch);
            }
            out.print("<br>");
            out.print("<br>");
            String toShow = "<label>There MAY BE one or more correct answers;";
            toShow += "<br>Make sure to enter them all</label>";
            out.print(toShow);
            out.print("<br>");
        %>

        <input type="text" name="correctAnswer" required placeholder="Enter correct answer" id="correctAnswer"/>
        <input type="submit" value="Submit Answer" id="submitButton">
    </form>
</div>
</body>
</html>

