<%--
  Created by IntelliJ IDEA.
  User: solo
  Date: 21.07.2020
  Time: 17:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
<h1>Home Page</h1>

<form action="${pageContext.request.contextPath}/login" method="post">
    Username: <input type="text" name="username"/><br>
    Password: <input type="password" name="password"/><br><br>
    <input type="submit" value="Login" id="loginButton">
</form>

<p><a href="/createAccount.jsp">Create New Account</a></p>


</body>
</html>
