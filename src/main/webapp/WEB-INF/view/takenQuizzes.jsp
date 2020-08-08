<%@ page import="ge.edu.freeuni.api.model.quiz.Quiz" %>
<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.edu.freeuni.api.model.passedQuiz.PassedQuiz" %><%--
  Created by IntelliJ IDEA.
  User: m.ormotsadze
  Date: 7/27/2020
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <title>User Page</title>

    <%
        List<PassedQuiz> takenQuizzes = (List<PassedQuiz>) request.getAttribute("takenQuizzes");
    %>

    <style>
        .bgimg {
            background-image: url(../../images/viewMoreQuizzesDarkvol2.jpg);
            min-height: 100%;
            background-position: center;
            background-size: cover;
        }

        * {
            box-sizing: border-box;
        }

        ul {
            align-content: center;
            margin: auto;
            max-width: 300px;
            list-style-type: none;
            padding: 0;
        }

        ul li {
            border-radius: 20px;
            background-color: #33ccff;
            color: red;
            font-family: Georgia, serif;
            font-size: 20px;
            text-align: left;
            max-width: 300px;
            border: 1px solid #ddd;
            margin: auto;
            padding: 12px;
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


    <p
            style="margin: auto; text-align: center; font-size: 50px; border-radius: 20px;
            color: white; stroke-width: 20px;
            font-family: Georgia, serif;
            max-width: 720px;
            padding: 12px; -webkit-text-stroke: 2px red;" >
        List of all taken quizzes:
    </p>

    <ul>
        <%
            for (PassedQuiz passedQuiz:
                    takenQuizzes) {
                String quizName = passedQuiz.getQuiz().getName();
                String toShow = "Quiz name: " + quizName + ", score: " + passedQuiz.getScore();
                long quizId = passedQuiz.getQuiz().getId();
                out.print(String.format("<a " +
                        "style=\"margin: auto; text-align: left; font-size: 25px; \n" +
                        "            color: orangered; stroke-width: 20px; \n" +
                        "            font-family: Georgia, serif;\n" +
                        "            max-width: 720px;\n" +
                        "           -webkit-text-stroke: 1px red;\" " +
                        "href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
            }
        %>
    </ul>

</div>

</body>
</html>
