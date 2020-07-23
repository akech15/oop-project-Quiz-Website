<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/index.css"/>
    <title>Home Page</title>
</head>
<body>

<div class="login-section">
    <div class="container">
        <div class="login-item">


        <h1>Home Page</h1>
        <form action="${pageContext.request.contextPath}/login" method="post">
        Username: <input type="text" name="username" placeholder="Enter User Name"/><br>
        Password: <input type="password" name="password" placeholder="Enter Password"/><br><br>
        <input class="btn" type="submit" value="Login" id="loginButton">
        </form>
        <p class="hplink"><a  href="/createAccount.jsp">Create New Account</a></p>
        </div>
    </div>
</div>

</body>
</html>
