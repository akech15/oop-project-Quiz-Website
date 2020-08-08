<%@ page import="ge.edu.freeuni.api.model.passedQuiz.PassedQuiz" %>
<%@ page import="ge.edu.freeuni.api.model.quiz.Quiz" %>
<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.edu.freeuni.api.model.challenge.Challenge" %>
<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage.css"/>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Challenges</title>

    <script>
        $(document).ready(function () {
            document.getElementById('challenges').style.display = 'block';
        });
    </script>

    <%
        List<Challenge> receivedChallenges = (List<Challenge>) request.getAttribute("receivedChallenges");
        User user = (User)request.getAttribute("user");
    %>

</head>
<body>


<div class="topnav">
    <a href="/logOut">Log Out</a>
    <a href="/messagingpage">Messages</a>
    <a class="active" href="/challengepage">Challenge</a>
    <a href="/friendrequestpage">Friend Request</a>
    <a href="/userhomepage">Home</a>
    <form class="example" action="/viewUsers" style="margin:auto;max-width:300px">
        <input type="text" id="searchInput" name="usernameFragment"
               onkeyup="searchFunction()" placeholder="Search People">
    </form>
    <p style=" font-size: 32px" id="welcome">Welcome <%=user.getName()%> :)</p>
</div>

<div class="bgimg w3-display-container w3-text-white">


    <div class="row">

        <div class="column">
            <h2 style="color: #33ccff">list of available quizzes</h2>
            <%
                List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
                int index = 0;
                for (Quiz quiz : quizzes) {
                    if (index == 10)
                        break;
                    String quizName = quiz.getName();
                    User creator = quiz.getCreator();
                    String creatorName = creator.getName();
                    String toShow = "name: " + quizName + ", creator: " + creatorName;
                    long quizId = quiz.getId();
                    out.print(String.format("<a " +
                            "style = \"border-color: red; " +
                            "font-size: 20px; border-width: medium;\" " +
                            "href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                    index++;
                }
            %>

            <%--        change count and view more logic--%>
            <a style = "border-color: black; font-size: 20px; color: red"
               href="/allAvailableQuizzes">view more</a>
        </div>

        <div class="column">
            <h2 style="color: #33ccff">list of taken quizzes</h2>
            <%
                List<PassedQuiz> passedQuizzes = (List<PassedQuiz>) request.getAttribute("passedQuizzes");
                index = 0;
                for (PassedQuiz passedQuiz : passedQuizzes) {
                    if (index == 10)
                        break;
                    String quizName = passedQuiz.getQuiz().getName();
                    long score = passedQuiz.getScore();
                    String duration = passedQuiz.getDuration();
                    String toShow = "quiz name: " + quizName + ", score: " + score + ", duration: " + duration;
                    out.print(String.format("<a " +
                                    "style = \"border-color: red; " +
                                    "font-size: 20px; border-width: medium;\" " +
                                    "href=\"/quizDescriptionPage/%d\">%s</a><br>",
                            passedQuiz.getQuiz().getId(), toShow));
                    index++;

                }
            %>
            <%--        change count and view more logic--%>
            <a style = "border-color: black; font-size: 20px; color: red"
               href="/allTakenQuizzes">view more</a>
        </div>

        <div class="column">
            <h2 style="color: #33ccff">list of recently made quizzes</h2>
            <%
                List<Quiz> userQuizes = (List<Quiz>) request.getAttribute("userQuizes");
                index = 0;
                for (Quiz quiz : userQuizes) {
                    if (index == 10)
                        break;
                    String quizName = quiz.getName();
                    String toShow = "name: " + quizName;
                    long quizId = quiz.getId();
                    out.print(String.format("<a " +
                            "style = \"border-color: red; " +
                            "font-size: 20px; border-width: medium;\" " +
                            "href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                    index++;
                }
            %>
            <a style = "border-color: black; font-size: 20px; color: red"
               href="/createNewQuiz">Create New Quiz</a>
        </div>
    </div>

</div>

<!-- challenges -->
<div id="challenges" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div id="requestBox" class="w3-container w3-display-container">
            <span onclick="document.getElementById('challenges').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>
            <h1>Challenges</h1>
        </div>
        <div class="w3-container">
            <%
                for (Challenge challenge: receivedChallenges) {

                    out.print(String.format(
                            "<h style=\"font-size:20px\" >" +
                                    "Sender: <b>%s</b>, name of quiz: <b>%s</b>, user's score: <b>%d</b>.  </h>",
                            challenge.getSender().getUsername(),
                            challenge.getQuiz().getName(),
                            challenge.getSenderScore()
                            )
                    );

                    out.print(String.format("<a style=\"font-size:20px\"" +
                            "href=\"/challenge/approve/%d\">  Approve </a>", challenge.getId()));

                    out.print(String.format("<a style=\"font-size:20px\"" +
                            "href=\"/challenge/delete/%d\">Delete</a><br>", challenge.getId()));
                }
            %>
        </div>
    </div>
</div>
<style>
    #requestBox {
        background-color: #800080;
        color: white;
    }
</style>

<script>
    window.onclick = function (event) {
        if (event.target === document.getElementById("challenges")) {
            document.getElementById("challenges").style.display = "none";
        }
    }
</script>

</body>
</html>
