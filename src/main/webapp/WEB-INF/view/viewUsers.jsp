<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: m.ormotsadze
  Date: 7/27/2020
  Time: 1:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/makeQuestions.css"/>
    <title>User Page</title>

    <%
        List<User> usersList = (List<User>) request.getAttribute("usersList");
        String usernameFragment = (String) request.getAttribute("usernameFragment");
    %>

    <style>
        .bgimg {
            background-image: url(../../images/userBackground.jpg);
            min-height: 100%;
            background-position: center;
            background-size: cover;
        }

        * {
            box-sizing: border-box;
        }

        ul {
            align-content: center;
            margin: auto;
            max-width: 300px;
            list-style-type: none;
            padding: 0;
        }

        ul li {
            border-radius: 20px;
            /*border-color: #33ccff;*/
            color: #33ccff;
            font-family: Georgia, serif;
            font-size: large;
            text-align: center;
            max-width: 300px;
            border: 1px solid #ddd;
            margin: auto; /* Prevent double borders */
            padding: 12px;
        }
    </style>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <h1 style="margin: auto; align-content: center">Users by username fragment: <%=usernameFragment%>
    </h1>

    <ul>
        <%
            for (User user :
                    usersList) {
                out.println("<li><a href=\"/viewUserPage/" + user.getId() + "\">" + user.getUsername() + "</a></li>");
            }
        %>
    </ul>

</div>

</body>
</html>
