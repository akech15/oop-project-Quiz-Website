<%@ page import="ge.edu.freeuni.api.model.quiz.Quiz" %>
<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.util.List" %><%--
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
        List<Quiz> available = (List<Quiz>) request.getAttribute("available");
    %>

    <style>
        .bgimg {
            background-image: url(../../images/search-people.jpg);
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
            text-align: center;
            max-width: 300px;
            border: 1px solid #ddd;
            margin: auto; /* Prevent double borders */
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
                style="margin: auto; text-align: center; font-size: 50px; color: black; border-radius: 20px;
            background-color: #33ccff;
            font-family: Georgia, serif;
            max-width: 720px;
            border: 1px solid #ddd;
            padding: 12px;">
            List of all available quizzes:
        </p>

        <ul>
            <%
                for (Quiz quiz :
                        available) {
                    String quizName = quiz.getName();
                    User creator = quiz.getCreator();
                    String creatorName = creator.getName();
                    String toShow = "name: " + quizName + ", creator: " + creatorName;
                    long quizId = quiz.getId();
                    out.print(String.format("<a " +
                            "style = \"border-color: red; " +
                            "font-size: 20px; border-width: medium;\" " +
                            "href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                }
            %>
        </ul>

    </div>

</body>
</html>
