<%@ page import="ge.edu.freeuni.api.model.passedQuiz.PassedQuiz" %>
<%@ page import="ge.edu.freeuni.api.model.friends.FriendshipStatusType" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Message</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script>
        $(document).ready(function () {
            document.getElementById('createChallenge').style.display = 'block';
        });
    </script>

        <%
            boolean valid = (boolean) request.getAttribute("valid");
            PassedQuiz passedQuiz = (PassedQuiz) request.getAttribute("passedQuiz");
            FriendshipStatusType friendshipStatus = (FriendshipStatusType) request.getAttribute("status");
        %>


<body>

<div class="bgimg w3-display-container w3-text-white">

    <div style="min-height: 400px" class="w3-display-topleft w3-container w3-xlarge">
        <p>
            <button onclick="document.getElementById('createChallenge').style.display='block'"
                    class="w3-button w3-black">
                Create Challenge
            </button>
        </p>
    </div>


    <div class="w3-display-topright w3-container w3-xlarge">
        <p>
            <button class="w3-button w3-black"><a href="/userhomepage">Home Page</a></button>
        </p>
        <p>
            <button class="w3-button w3-black"><a href="/">Log Out</a></button>
        </p>
    </div>

</div>

<!-- Log In Form -->
<div id="createChallenge" class="w3-modal">
    <div class="w3-modal-content w3-animate-zoom">
        <div class="w3-container w3-black w3-display-container">
            <span onclick="document.getElementById('createChallenge').style.display='none'"
                  class="w3-button w3-display-topright w3-large">x</span>

            <%
               String toPrint;
               if(!valid){
                   toPrint = "Invalid Username!";
               } else if(!friendshipStatus.equals(FriendshipStatusType.APPROVED)){
                   toPrint = "U and the user aren't friends yet!";
               } else {
                   toPrint = "Create Challenge";
               }
            %>

            <h1><%=toPrint%></h1>

        </div>
        <div class="login-item">
            <form action="/sendChallenge/<%=passedQuiz.getId()%>" method="post">

                <label> Receiver Username:</label>
                <input type="text" id="receiverUsername" name="receiverUsername" required
                       placeholder="Enter receiver username"><br>

                <input type="submit" id="submitButton" value="Send Challenge">
            </form>

        </div>
    </div>
</div>

</body>
</html>


