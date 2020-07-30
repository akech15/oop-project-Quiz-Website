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

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home Page</a></button></p>
        <p><button class="w3-button w3-black"><a  href="/">Log Out</a></button></p>
    </div>

    <form action="${pageContext.request.contextPath}/addMultipleAnswers" method="post">
        <%
            Question question = (Question) request.getAttribute("question");
            String q = question.getQuestion();
            Integer choice = (Integer) request.getAttribute("index");
            out.print("<h1> Question: #" + choice + " : "+ q + "</h1><br>");
            List<String> answers = question.getChoices();
            List<Character> chars = new ArrayList<>();
            char ch = 'a';
            int id = 0;
            out.print("<h2> Possible Answers: </h2><br>");
            for (int i = 0; i < answers.size(); i++, ch++, id++){
                out.print("<h2> " + ch + ":  </h2>");
                out.print("<h2> " + answers.get(i) + " </h2>");
                out.print("<br>");
                chars.add(ch);
            }
            out.print("<br>");
            out.print("<br>");
            String toShow = "<label>Enter if correct answer is either ";
            for(int i = 0; i < chars.size() - 1; i++){
                toShow += chars.get(i) +  ", ";
            }
            toShow += "or " + chars.get(chars.size() - 1);
            toShow += "</label>";
            out.print(toShow);
            out.print("<br>");
        %>

        <input type="text" name="correctAnswer" required placeholder="Enter correct answer" id = "correctAnswer"/>
        <input type="submit" value = "Submit Answer" id="submitButton">
    </form>
</div>
</body>
</html>

