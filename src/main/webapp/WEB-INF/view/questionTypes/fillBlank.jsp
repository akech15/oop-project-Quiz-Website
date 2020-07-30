<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/fillBlank.css"/>
    <title>Make Questions</title>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">


    <div class="w3-display-topright w3-container w3-xlarge">
        <p><button class="w3-button w3-black"><a  href="/userhomepage">Home Page</a></button></p>
        <p><button class="w3-button w3-black"><a  href="/">Log Out</a></button></p>
    </div>

    <h1>Make Questions</h1>~
    <form class="select">
        <label>Choose question type:</label>
        <select id = "questionType" name = "questionType">
            <option>Fill In Blank</option>
            <option>Multiple Choice</option>
            <option>True/False</option>
            <option>Fill In Multiple Blanks</option>
            <option>Multiple Answers</option>
            <option>Image Answers</option>
            <option>Matching</option>
            <option>Question/Response</option>
        </select>

        <div class="select_arrow">
        </div><br>
    </form>
        <form action="${pageContext.request.contextPath}/addFillInBlankQuestion" method="post">
            <label>Enter first part of the question:</label>
            <textarea id="firstPart" name = "firstPart" required placeholder = "Enter text" cols = 60></textarea><br>
            <label>Enter second part of the question:</label>
            <textarea id="secondPart" name = "secondPart" required placeholder = "Enter text" cols = 60></textarea><br>
            <label>Enter omitted part of the question:</label>
            <textarea id="blank" name = "blank" required placeholder = "Enter what should be blank" cols = 60></textarea><br>
            <input type="submit" value = "Add question" id="submitButton">
        </form>

        <form action="${pageContext.request.contextPath}/makeQuestions" method="post">
            <input class="btn" type="submit" value="Make Another Question" id="makeQuestions"><br>
        </form>

        <form action="${pageContext.request.contextPath}/viewQuiz" method="post">
            <input class="btn" type="submit" value="Finish Making Quiz" id="finishQuiz"><br>
        </form>
</div>

<script>
    window.onclick = function(event) {

        if (event.target == document.getElementById("questionType")){
            if(document.getElementById("questionType").value == "Multiple Choice") {
                window.location.href = '/multipleChoice';
            } else if(document.getElementById("questionType").value == "True/False") {
                window.location.href = '/trueFalse';
            } else if(document.getElementById("questionType").value == "Fill In Multiple Blanks") {
                window.location.href = '/fillMultipleBLank';
            } else if(document.getElementById("questionType").value == "Multiple Answers") {
                window.location.href = '/multipleAnswers';
            } else if(document.getElementById("questionType").value == "Image Answers") {
                window.location.href = '/imageAnswers';
            } else if(document.getElementById("questionType").value == "Matching") {
                window.location.href = '/matching';
            } else if(document.getElementById("questionType").value == "Question/Response") {
                window.location.href = '/questionResponse';
            }
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

