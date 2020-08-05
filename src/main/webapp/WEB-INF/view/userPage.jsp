<%@ page import="ge.edu.freeuni.api.model.passedQuiz.PassedQuiz" %>
<%@ page import="ge.edu.freeuni.api.model.quiz.Quiz" %>
<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.edu.freeuni.server.services.user.UserService" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage.css"/>

    <title>User Page</title>

    <%
        User user = (User) request.getAttribute("user");
    %>

</head>
<body>


<div class="topnav">
    <a href="/logOut">Log Out</a>
    <a href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a href="/viewUserPage/2">Friend Requests</a>
    <a class="active" href="/userhomepage">Home</a>

    <form class="example" action="/viewUsers" style="margin:auto;max-width:300px">

        <input type="text" id="searchInput" name="usernameFragment"
               onkeyup="searchFunction()" placeholder="Search People">

    </form>
    <p id="welcome">Welcome <%=user.getName()%>, here is your quizzes</p>
</div>

<div class="bgimg w3-display-container w3-text-white">


    <div class="row">
        <div class="column">
            <h2>list of available quizzes</h2>
            <%
                List<Quiz> quizzes = (List<Quiz>) request.getAttribute("quizzes");
                int index = 0;
                for (Quiz quiz : quizzes) {
                    if (index == 10)
                        break;
                    String quizName = quiz.getName();
                    User creator = quiz.getCreator();
                    String creatorName = creator.getUsername();
                    String toShow = "name: " + quizName + ", creator: " + creatorName;
                    long quizId = quiz.getId();
                    out.print(String.format("<a href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                    index++;
                }
            %>

            <%--        change count and view more logic--%>
            <a href="/friendrequestpage">view more</a>
        </div>

        <div class="column">
            <h2>list of taken quizzes</h2>
            <%
                List<PassedQuiz> passedQuizzes = (List<PassedQuiz>) request.getAttribute("passedQuizzes");
                index = 0;
                for (PassedQuiz passedQuiz : passedQuizzes) {
                    if (index == 10)
                        break;
                    String quizName = passedQuiz.getQuiz().getName();
                    long score = passedQuiz.getScore();
                    String toShow = "quiz name: " + quizName + ", score: " + score;
                    out.print(String.format("<a href=\"/quizDescriptionPage/%d\">%s</a><br>",
                            passedQuiz.getQuiz().getId(), toShow));
                    index++;

                }
            %>
            <%--        change count and view more logic--%>
            <a href="/friendrequestpage">view more</a>
        </div>

        <div class="column">
            <h2>list of recently made quizzes</h2>
            <%
                List<Quiz> userQuizes = (List<Quiz>) request.getAttribute("userQuizes");
                index = 0;
                for (Quiz quiz : userQuizes) {
                    if (index == 10)
                        break;
                    String quizName = quiz.getName();
                    String toShow = "name: " + quizName;
                    long quizId = quiz.getId();
                    out.print(String.format("<a href=\"/quizDescriptionPage/%d\">%s</a><br>", quizId, toShow));
                    index++;
                }
            %>
            <a href="/createNewQuiz">create new quiz</a>
        </div>
    </div>

</div>
<ul id="searchUL">

</ul>

<%--<script src="../../searchSuggestions.js"></script>--%>

</body>
</html>
