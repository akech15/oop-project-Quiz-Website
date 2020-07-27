<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage.css" />
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <title>Friend Requests</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <script>
        $(document).ready(function(){
            document.getElementById('friendRequests').style.display='block';
        });
    </script>

</head>
<body>


<div class="topnav">
    <a href="/userhomepage">Home</a>
    <a href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a class="active" href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="??????????" style="margin:auto;max-width:300px">

        <input type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search People">

    </form>
</div>

<div class="bgimg w3-display-container w3-text-white">
    <div class="row">
        <div class="column" >
            <h2>list of available quizzes</h2>
            <%
                List<String> quizNames = (List<String>) request.getAttribute("quizNames");
                for (String quizName : quizNames) {
                    out.print(String.format("<a href=\"/quizDescriptionPage/%s\">%s</a><br>", quizName, quizName));
                }
            %>

            <%--        change count and view more logic--%>
            <a href="/login">view more</a>
        </div>
        <div class="column">
            <h2>list of taken quizes</h2>
            <p>quiz1</p>
            <%--        change count and view more logic--%>
            <a href="/login">view more</a>
        </div>
        <div class="column" >
            <h2>my quizzes</h2>
            <%--        change count and view more logic--%>
            <a href="/createQuizPage">create new quiz</a>
        </div>
    </div>

</div>

<!-- Friend Requests -->
<div id="friendRequests" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div id = "requestBox" class="w3-container w3-display-container">
            <span onclick="document.getElementById('friendRequests').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>Friend Requests</h1>
        </div>

        <div class="w3-container">
            <h5>NEWS: <b>YOU HAVE NO FRIENDS</b></h5>
        </div>
    </div>
</div>
<style>
    #requestBox{
        background-color: #800080;
        color:white;
    }
</style>

<script>
    window.onclick = function(event) {
        if (event.target == document.getElementById("friendRequests")) {
            document.getElementById("friendRequests").style.display = "none";
        }
    }
</script>

<ul id="searchUL">

</ul>

<script src="../../searchSuggestions.js"></script>

</body>
</html>
