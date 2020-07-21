<%--
  Created by IntelliJ IDEA.
  User: m.ormotsadze
  Date: 7/21/2020
  Time: 9:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>OOPs</title>
</head>
<body>
    <h1>either username or password is incorrect</h1>
    <h1> please try again </h1>

    <form action="${pageContext.request.contextPath}/login" method="post">
        Username: <input type="text" name="username"/><br>
        Password: <input type="password" name="password"/><br><br>
        <input type="submit" value="Login" id="loginButton">
    </form>

    <p><a href="${pageContext.request.contextPath}/createAccount">Create New Account</a></p>

</body>
</html>
