<%--
  Created by IntelliJ IDEA.
  User: m.ormotsadze
  Date: 7/26/2020
  Time: 2:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ABOUT QUIZ</title>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/quizDescription.css"/>
</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p><button onclick="document.getElementById('aboutQuiz').style.display='block'" class="w3-button w3-black">About this quiz</button></p>
        <p><button onclick="document.getElementById('aboutAuthor').style.display='block'" class="w3-button w3-black">About author</button></p>
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home Page</a></button></p>
        <p><button class="w3-button w3-black"><a  href="/">Log Out</a></button></p>
    </div>

    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/preQuiz">Start Quiz</a></button></p>
    </div>

</div>
<!-- About Quiz -->
<div id="aboutQuiz" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('aboutQuiz').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>About Quiz</h1>
        </div>

        <div class="w3-container">
            <h5>Name: <b>bazidan wamogebuli info</b></h5>
            <h5>Description: <b>bazidan wamogebuli info</b></h5>
            <h5>creation date: <b>bazidan wamogebuli info</b></h5>
        </div>
    </div>
</div>


<!-- start Quiz -->
<div id="startQuiz" class="button">

</div>

</body>
</html>