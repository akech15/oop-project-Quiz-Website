<%@ page import="jdk.nashorn.internal.ir.RuntimeNode" %><%--
  Created by IntelliJ IDEA.
  User: solo
  Date: 21.07.2020
  Time: 17:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //String[] arr = (String[]) request.getAttribute("arr");
    String username = request.getParameter("username");

%>

<html>
<head>
    <title>Login</title>
</head>
<body>

<h1> Welcome  ${username} </h1>

<%


        out.print(username);
        out.print("qnaa");
        out.print("<br>");
    //}
%>

</body>
</html>
