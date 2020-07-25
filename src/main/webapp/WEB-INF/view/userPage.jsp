<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage.css" />

    <title>User Page</title>

</head>
<div class="header">
    <div contenteditable>
        QUI<span style="color: gold">ZZ</span>ERS
    </div>
</div>
<body>


<div class="topnav">
    <a class="active" href="/userhomepage">Home</a>
    <a href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="??????????" style="margin:auto;max-width:300px">

        <input type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search People">

    </form>
    <p id = "welcome">Welcome ${username}, here is your quizes</p>
</div>


<div class="row">
    <div class="column" >
        <h2>List of most popular quizzes</h2>
        <p>quiz 1...</p>
        <p>quiz 2...</p>
        <p>quiz 3...</p>
        <p>quiz 4...</p>
        <p>quiz 5...</p>
        <p>quiz 6...</p>
        <p>quiz 7...</p>
        <p>quiz 8...</p>
        <p>quiz 9...</p>
        <p>quiz 10...</p>
<%--        change count and view more logic--%>
        <a href="/friendrequestpage">view more</a>
    </div>
    <div class="column">
        <h2>List of taken quizzes</h2>
        <p>quiz1</p>
        <%--        change count and view more logic--%>
        <a href="/friendrequestpage">view more</a>
    </div>
    <div class="column" >
        <h2>My quizzes</h2>
        <%--        change count and view more logic--%>
        <a href="/createQuiz.jsp">create new quiz</a>
    </div>
</div>

<ul id="searchUL">

</ul>

<script src="../../searchSuggestions.js"></script>

</body>
</html>
