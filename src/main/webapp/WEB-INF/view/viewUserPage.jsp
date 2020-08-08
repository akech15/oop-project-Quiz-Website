<%@ page import="ge.edu.freeuni.api.model.friends.FriendshipStatusType" %>
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
    <%
        User user = (User) request.getAttribute("user");
        FriendshipStatusType friendshipStatusType = (FriendshipStatusType) request.getAttribute("friendShipType");
    %>

    <style>
        .bgimg {
            background-image: url(../../images/userBackground.jpg);
            min-height: 100%;
            background-position: center;
            background-size: cover;
        }
    </style>
    <title>User Page</title>
</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p>
            <button onclick="document.getElementById('aboutUser').style.display='block'" class="w3-button w3-black">
                About User
            </button>
        </p>
        <p>
            <button onclick="document.getElementById('usersQuizzes').style.display='block'" class="w3-button w3-black">
                Created Quizzes
            </button>
        </p>
        <p>
            <button onclick="document.getElementById('passedQuizzes').style.display='block'" class="w3-button w3-black">
                Passed Quizzes
            </button>
        </p>
        <%
            long activeUsrId = (long) request.getAttribute("activeUsrId");
            if(activeUsrId != user.getId()){
                if (friendshipStatusType.equals(FriendshipStatusType.STRANGERS)) {
                    out.print(" <p>\n" + "<button class=\"w3-button w3-black\"><a href=\"/friendRequest/" +
                            user.getId() + "/send\">Send Friend Request</a></button>\n" + "</p>");
                } else if (friendshipStatusType.equals(FriendshipStatusType.PENDING)) {
                    out.print(" <p>\n" + "<button class=\"w3-button w3-black\"><a href=\"/friendRequest/" +
                            user.getId() + "/cancel\">Cancel Request</a></button>\n" + "</p>");
                } else {
                    out.print(" <p>\n" + "<button class=\"w3-button w3-black\"><a href=\"/friendRequest/" +
                            user.getId() + "/remove\">Remove Friend</a></button>\n" + "</p>");
                }
            }
        %>
    </div>


    <div class="w3-display-topright w3-container w3-xlarge">
        <p>
            <button class="w3-button w3-black"><a href="/userhomepage">Home</a></button>
        </p>
    </div>

</div>

<!-- About User -->
<div id="aboutUser" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('aboutUser').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>User Info</h1>
        </div>
        <div class="w3-container">
            <h5>Name: <b><%=user.getName()%></b>
            </h5>
            <h5>Username: <b><%=user.getUsername()%></b></h5>
        </div>
    </div>
</div>


<!-- User's created quizzes -->
<div id="usersQuizzes" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('usersQuizzes').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>User's Quizzes</h1>
        </div>
        <div class="w3-container">
            <%
                List<Quiz> userQuizes = (List<Quiz>) request.getAttribute("userQuizzes");
                int index = 0;
                for (Quiz quiz : userQuizes) {
                    if (index == 10)
                        break;
                    String quizName = quiz.getName();
                    String toShow = "Name: " + quizName;
                    long quizId = quiz.getId();
                    out.print(String.format("<a href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                    index++;
                }
            %>
        </div>
    </div>
</div>

<div id="passedQuizzes" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('passedQuizzes').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>User's Quizzes</h1>
        </div>
        <div class="w3-container">
            <%
                List<PassedQuiz> passedQuizzes = (List<PassedQuiz>) request.getAttribute("passedQuizzes");
                index = 0;
                for (PassedQuiz quiz : passedQuizzes) {
                    if (index == 10)
                        break;
                    String quizName = quiz.getQuiz().getName();
                    String toShow = "Name: " + quizName + ", " + "Score: " + quiz.getScore();
                    long quizId = quiz.getId();
                    out.print(String.format("<a href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                    index++;
                }
            %>
        </div>
    </div>
</div>


<script>
    window.onclick = function (event) {
        if (event.target === document.getElementById("aboutUser")) {
            document.getElementById("aboutUser").style.display = "none";
        } else if (event.target === document.getElementById("usersQuizzes")) {
            document.getElementById("usersQuizzes").style.display = "none";
        } else if (event.target === document.getElementById("passedQuizzes")) {
            document.getElementById("passedQuizzes").style.display = "none";
        }
    }
</script>

</body>
</html>
