<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="java.io.IOException" %>
<!DOCTYPE html>
<html>
<title>QUIZ TIME</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="${pageContext.request.contextPath}/index.css"/>


<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p><button onclick="document.getElementById('login').style.display='block'" class="w3-button w3-black">Log In</button></p>
        <p><button onclick="document.getElementById('about').style.display='block'" class="w3-button w3-black">About Quiz Time</button></p>
    </div>


    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button onclick="document.getElementById('topUsers').style.display='block'" class="w3-button w3-black">Top Rated Users</button></p>
        <p><button onclick="document.getElementById('topQuizzes').style.display='block'" class="w3-button w3-black">Top Rated Quizzes</button></p>
    </div>

</div>

<!-- Log In Form -->
<div id="login" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('login').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>Log In</h1>
        </div>
        <div class="login-item">
            <form action="${pageContext.request.contextPath}/login" method="post">
                Username: <input type="text" name="username" placeholder="Enter User Name" id = "usernameBox"/><br>
                Password: <input type="password" name="password" placeholder="Enter Password" id = "passwordBox"/><br><br>
                <input class="btn" type="submit" value="Login" id="loginButton">
            </form>
            <p class="hplink"><a  href="/createAccount.jsp">Create New Account</a></p>
        </div>
    </div>
</div>

<!-- About Quiz Time -->
<div id="about" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black">
            <span onclick="document.getElementById('about').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>About Quiz time</h1>
        </div>
        <div class="w3-container">
            <%String fileName = "WEB-INF/AboutQuizTime.txt";
            InputStream ins = application.getResourceAsStream(fileName);
            try {
                if(ins == null) {
                    response.setStatus(response.SC_NOT_FOUND);
                } else {
                    BufferedReader br = new BufferedReader((new InputStreamReader(ins)));
                    String line;
                    while((line = br.readLine())!= null) {
                        out.println(line + "<br>");
                    }
                }
            } catch(IOException e) {
                out.println(e.getMessage());
            }
            %>
        </div>
    </div>
</div>

<!-- Top rated users -->
<div id="topUsers" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('topUsers').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>Top Rated Users</h1>
        </div>
    </div>
</div>

<!-- Top rated quizzes -->
<div id="topQuizzes" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('topQuizzes').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>Top Rated Quizes</h1>
        </div>
    </div>
</div>

<script>
    window.onclick = function(event) {
        if (event.target == document.getElementById("login")) {
            document.getElementById("login").style.display = "none";
        } else if(event.target == document.getElementById("about")){
            document.getElementById("about").style.display = "none";
        } else if(event.target == document.getElementById("topUsers")){
            document.getElementById("topUsers").style.display = "none";
        } else if(event.target == document.getElementById("topQuizzes")){
            document.getElementById("topQuizzes").style.display = "none";
        }
    }

</script>
</body>
</html>

