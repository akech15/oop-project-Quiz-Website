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
    <title>Sign up</title>
</head>
<body>
<h1>please enter username and password</h1>

<form action="${pageContext.request.contextPath}/createAccount" method="post">
    Username: <input type="text" name="username"/><br>
    Password: <input type="password" name="password"/><br><br>
    <input type="submit" value="create Account" id="loginButton">
</form>

</body>
</html>