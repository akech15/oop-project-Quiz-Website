<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/homepage.css" />

</head>
<body>


<div class="topnav">
    <a class="active" href="/userhomepage">Home</a>
    <a href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="/action_page.php" style="margin:auto;max-width:300px">

        <input type="text" id="myInput" onkeyup="searchSuggestions()" placeholder="Search People">

    </form>
    <p id = "welcome">Welcome ${username}, here is your quizes</p>
</div>


<div class="row">
    <div class="column" >
        <h2>list of available quizes</h2>
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
        <h2>list of taken quizes</h2>
        <p>quiz1</p>
        <%--        change count and view more logic--%>
        <a href="/friendrequestpage">view more</a>
    </div>
    <div class="column" >
        <h2>my quizes</h2>
        <%--        change count and view more logic--%>
        <a href="/friendrequestpage">create new quiz</a>
    </div>
</div>

<ul id="searcBox">

</ul>

<script>
    function searchSuggestions() {
        var input, filter, ul, li, a, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        ul = document.getElementById("searcBox");
        li = ul.getElementsByTagName("li");
        for (i = 0; i < li.length; i++) {
            a = li[i].getElementsByTagName("a")[0];
            txtValue = a.textContent || a.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                li[i].style.display = "";
            } else {
                li[i].style.display = "none";
            }
        }
    }
</script>

</body>
</html>
