<%@ page import="ge.edu.freeuni.api.model.quiz.Quiz" %>
<%@ page import="ge.edu.freeuni.api.model.user.User" %>
<%@ page import="java.util.List" %>
<%@ page import="ge.edu.freeuni.api.model.mail.Mail" %><%--
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
        Mail mail = (Mail) request.getAttribute("mail");
    %>

    <style>
        .bgimg {
            background-image: url(../../images/1.jpg);
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
            font-size: 20px;
            max-width: 700px;
            list-style-type: none;
            padding: 0;
        }

        /*ul li {*/
        /*    border-radius: 20px;*/
        /*    background-color: #33ccff;*/
        /*    color: red;*/
        /*    font-family: Georgia, serif;*/
        /*    font-size: 20px;*/
        /*    text-align: center;*/
        /*    border: 1px solid #ddd;*/
        /*    margin: auto; !* Prevent double borders *!*/
        /*    padding: 12px;*/
        /*}*/
    </style>

</head>
<body>

<div class="bgimg w3-display-container w3-text-white">

    <div class="w3-display-topright w3-container w3-xlarge">
        <p>
            <button class="w3-button w3-black"><a href="/userhomepage">Home Page</a></button>
        </p>
        <p>
            <button class="w3-button w3-black"><a href="/">Log Out</a></button>
        </p>
        <p>
            <button class="w3-button w3-black"><a href="/messagingpage">B 2 Msgs</a></button>
        </p>
    </div>


    <p
            style="margin: auto; text-align: center; font-size: 60px; border-radius: 20px;
            font-family: Georgia, serif;
            color: red;
            padding: 12px;">
        Email Info:
    </p>

    <ul>
        <%
            String toPrint = String.format(
                    "<p>\n" +
                    "     <span style=\"color: red; font-size: 40px\">Sender: </span>\n" +
                    "     <span style=\"color: white; font-size: 40px\"><a href=\"/viewUserPage/%d\">%s</a></span>\n" +
                    " </p>\n" +
                    " <p>\n" +
                    "     <span style=\"color: red; font-size: 40px\">Receiver: </span>\n" +
                    "     <span style=\"color: white; font-size: 40px\"><a href=\"/viewUserPage/%d\">%s</a></span>\n" +
                    "  </p>\n" +
                    "  <p>\n" +
                    "     <span style=\"color: red; font-size: 40px\">Sent date: </span>\n" +
                    "     <span style=\"color: white; font-size: 25px\">%s</span>\n" +
                    "</p>\n" +
                    "\n" +
                    " <p style=\"color: red; font-size: 40px\">Context:</p>\n" +
                    " <p>%s</p>",
                    mail.getSender().getId(),
                    mail.getSender().getUsername(),
                    mail.getReceiver().getId(),
                    mail.getReceiver().getUsername(),
                    mail.getSent_date(),
                    mail.getContext()
            );
            out.print(toPrint);
        %>

    </ul>

</div>

</body>
</html>
