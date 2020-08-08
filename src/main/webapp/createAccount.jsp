<!DOCTYPE html>
<html>
<title>Create Account</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="${pageContext.request.contextPath}/index.css"/>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function(){
            document.getElementById('login').style.display='block';
        });
    </script>

    <style>
        #nameBox {
            border-radius: 25px;
            border: 2px solid #609;
            padding: 20px;
            width: 200px;
            height: 15px;
            outline: none;
        }
    </style>
</head>
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
            <h1>Sign Up</h1>
        </div>
        <div class="login-item">
            <form action="${pageContext.request.contextPath}/createAccount" method="post">
                Name: <input type="text" name="name" placeholder="Enter Name" id = "nameBox"/><br>
                Username: <input type="text" name="username" placeholder="Enter Username" id = "usernameBox"/><br>
                Password: <input type="password" name="password" placeholder="Enter Password" id = "passwordBox"/><br><br>
                <input class="btn" type="submit" value="Create Account" id="loginButton">
            </form>
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
            <p>this side is bla bla bla</p>
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

</body>
</html>

