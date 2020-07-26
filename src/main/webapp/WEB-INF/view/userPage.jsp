<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage.css"/>

    <title>User Page</title>

</head>
<body>


<div class="topnav">
    <a class="active" href="/userhomepage">Home</a>
    <a href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="??????????" style="margin:auto;max-width:300px">

        <input type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search People">

    </form>
    <p id="welcome">Welcome ${username}, here is your quizzes</p>
</div>

<div class="bgimg w3-display-container w3-text-white">


    <div class="row">
        <div class="column">
            <h2>list of available quizes</h2>
            <%
                List<String> quizNames = (List<String>) request.getAttribute("quizNames");
                for (String quizName : quizNames) {
                    out.print(String.format("<a href=\"/quizDescriptionPage/%s\">%s</a><br>", quizName, quizName));

                }
            %>

            <%--        change count and view more logic--%>
            <a href="/friendrequestpage">view more</a>
        </div>
        <div class="column">
            <h2>list of taken quizes</h2>
            <p>quiz1</p>
            <%--        change count and view more logic--%>
            <a href="/friendrequestpage">view more</a>
        </div>
        <div class="column">
            <h2>my quizzes</h2>
            <%--        change count and view more logic--%>
            <a href="/friendrequestpage">create new quiz</a>
        </div>
    </div>

</div>
<ul id="searchUL">

</ul>

<script src="../../searchSuggestions.js"></script>

</body>
</html>
