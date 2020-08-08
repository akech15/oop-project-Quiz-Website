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

    <h1>Make Questions</h1>~
    <form class="select">
        <label>Choose question type:</label>
        <select id = "questionType" name = "questionType">
            <option>Fill In Blank</option>
            <option>Multiple Choice</option>
            <option>True/False</option>
            <option>Image Answers</option>
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
    }



</script>
</body>
</html>

