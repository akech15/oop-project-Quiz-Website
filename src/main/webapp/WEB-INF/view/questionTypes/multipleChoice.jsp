<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/createQuiz.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/multipleChoice.css"/>
    <title>Make Questions</title>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div>
        <h1>Make Questions</h1>~
        <form class="select">
            <label>Choose question type:</label>

            <select id = "questionType" name = "questionType">
                <option>Multiple Choice</option>
                <option>True/False</option>
                <option>Fill In Blank</option>
                <option>Image Answers</option>
                <option>Question/Response</option>
            </select>

            <div class="select_arrow">
            </div><br>
        </form>

        <form action="${pageContext.request.contextPath}/multipleChoiceSubmitted" method="post">
            <label for = "choiceCount">Enter number of choices: </label>
            <input type="number" name="choiceCount" required placeholder="3-7" id = "choiceCount" min = "3" max = "7"/>
            <input type="submit" value = "Add question" id="submitButton">
        </form>


    </div>
</div>

<script>
    window.onclick = function(event) {
        if (event.target == document.getElementById("questionType")){
            if(document.getElementById("questionType").value == "True/False") {
                window.location.href = '/trueFalse';
            } else if(document.getElementById("questionType").value == "Fill In Blank") {
                window.location.href = '/fillBlank';
            } else if(document.getElementById("questionType").value == "Fill In Multiple Blanks") {
                window.location.href = '/fillMultipleBlanks';
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

