<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css" />


    <title>Messages</title>
</head>
<body>

<div class="topnav">
    <a href="/userhomepage">Home</a>
    <a class="active" href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="??????" style="margin:auto;max-width:300px">
        <input type="text" id="searchInput" onkeyup="searchFunction()" placeholder="Search People">
    </form>
</div>



<ul id="searchUL">

</ul>

<script src="../../searchSuggestions.js"></script>

</body>
</html>
