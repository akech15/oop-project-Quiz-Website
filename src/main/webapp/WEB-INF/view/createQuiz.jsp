<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <title>Create Quiz</title>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topleft w3-container w3-xlarge">
        <p><button onclick="document.getElementById('createQuiz').style.display='block'" class="w3-button w3-black">Create Quiz</button></p>

    </div>


    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home Page</a></button></p>
        <p><button class="w3-button w3-black"><a  href="/">Log Out</a></button></p>
    </div>

</div>

<!-- Log In Form -->
<div id="createQuiz" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('createQuiz').style.display='none'" class="w3-button w3-display-topright w3-large">x</span>
            <h1>Create Quiz</h1>
        </div>
        <div class="login-item">
            <form action="${pageContext.request.contextPath}/startQuizMaking" method="post">
                <label for = "quizName">Quiz Name:</label>
                <input type = "text" id = "quizName" name = "quizName" required placeholder="Enter Quiz Name"><br>

                <label for = "quizName">Description:</label>
                <textarea id="description" name = "description" required placeholder = "Tell us about your quiz" cols = 60></textarea><br>
                <input type = "submit" id = "submitButton" value = "Make Questions">
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
        <p>lol</p>
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
        if (event.target == document.getElementById("createQuiz")) {
            document.getElementById("createQuiz").style.display = "none";
        }
        // else if(event.target == document.getElementById("about")){
        //     document.getElementById("about").style.display = "none";
        // } else if(event.target == document.getElementById("topUsers")){
        //     document.getElementById("topUsers").style.display = "none";
        // } else if(event.target == document.getElementById("topQuizzes")){
        //     document.getElementById("topQuizzes").style.display = "none";
        // }
    }

</script>
</body>
</html>

