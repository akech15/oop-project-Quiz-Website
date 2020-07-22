<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css" />
</head>
<body>


<div class="topnav">
    <a href="/userhomepage">Home</a>
    <a href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a class="active" href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="/action_page.php" style="margin:auto;max-width:300px">
        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search People">
    </form>
</div>



<ul id="myUL">

</ul>

</body>
</html>
