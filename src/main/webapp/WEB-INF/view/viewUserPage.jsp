<%--
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
</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p><button onclick="document.getElementById('aboutUser').style.display='block'" class="w3-button w3-black">About User</button></p>
        <p><button onclick="document.getElementById('usersQuizzes').style.display='block'" class="w3-button w3-black">Quizzes</button></p>
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Send Friend Request</a></button></p>
    </div>


    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home</a></button></p>
    </div>

</div>

<!-- About User -->
<div id="aboutUser" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('aboutUser').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>User Info</h1>
        </div>
        <div class="w3-container">
            <h5>saxeliiii da gvari</h5>
            <h5>dab dge da egetobebi</h5>
        </div>
    </div>
</div>


<!-- User's created quizzes -->
<div id="usersQuizzes" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('usersQuizzes').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>User's Quizzes</h1>
        </div>
        <div class="w3-container">
            <h5>linkebi raebi aqvs sheqmnili</h5>
        </div>
    </div>
</div>

<script>
    window.onclick = function(event) {
        if (event.target == document.getElementById("aboutUser")) {
            document.getElementById("aboutUser").style.display = "none";
        } else if(event.target == document.getElementById("usersQuizzes")){
            document.getElementById("usersQuizzes").style.display = "none";
        }
    }
</script>

</body>
</html>
