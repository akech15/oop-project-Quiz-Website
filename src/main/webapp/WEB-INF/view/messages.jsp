<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/controlbar.css" />
</head>
<body>


<div class="topnav">
    <a href="/userhomepage">Home</a>
    <a class="active" href="/messagingpage">Messages</a>
    <a href="/challengepage">Challenge</a>
    <a href="/friendrequestpage">Friend Requests</a>

    <form class="example" action="/action_page.php" style="margin:auto;max-width:300px">
        <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Search People">
    </form>
</div>



<ul id="myUL">

</ul>

<script>
    function myFunction() {
        var input, filter, ul, li, a, i, txtValue;
        input = document.getElementById("myInput");
        filter = input.value.toUpperCase();
        ul = document.getElementById("myUL");
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
