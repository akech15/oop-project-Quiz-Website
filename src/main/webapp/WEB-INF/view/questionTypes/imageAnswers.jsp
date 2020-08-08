<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoice.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoiceSubmitted.css"/>
    <title>Make Questions</title>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">


    <h1>Make Questions</h1>
    <form class="select">
        <label>Choose question type:</label>
        <select id = "questionType" name = "questionType">
            <option>Image Answers</option>
            <option>Question/Response</option>
            <option>Multiple Choice</option>
            <option>True/False</option>
            <option>Fill In Blank</option>
        </select>

        <div class="select_arrow">
        </div><br>
    </form>
    <form action="${pageContext.request.contextPath}/imageAnswersSubmitted" method="post">
        <label for = "choiceCount">Enter number of possible answers: </label>
        <input type="number" name="choiceCount" required placeholder="1-5" id = "choiceCount" min = "1" max = "5"/>
        <input type="submit" value = "Add question" id="submitButton">
    </form>
</div>

<script>
    window.onclick = function(event) {
        if (event.target == document.getElementById("questionType")){
            if(document.getElementById("questionType").value == "Multiple Choice") {
                window.location.href = '/multipleChoice';
            } else if(document.getElementById("questionType").value == "True/False") {
                window.location.href = '/trueFalse';
            } else if(document.getElementById("questionType").value == "Fill In Blank") {
                window.location.href = '/fillBlank';
            } else if(document.getElementById("questionType").value == "Fill In Multiple Blanks") {
                window.location.href = '/fillMultipleBlanks';
            } else if(document.getElementById("questionType").value == "Multiple Answers") {
                window.location.href = '/multipleAnswers';
            }else if(document.getElementById("questionType").value == "Matching") {
                window.location.href = '/matching';
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

