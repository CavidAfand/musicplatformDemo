<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/29/2020
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
<%--    <link rel="stylesheet" href="css/signup.css?dev=12">--%>
    <link rel="stylesheet" href="css/home.css">
</head>
<body>

<%--    <jsp:include page="components/navbar.jsp"/>--%>

    <div></div>

    <h1>Home page</h1>
    <h2>Listener</h2>

    <p>Name: ${user.date}</p>

    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button type="submit">Logout</button>
    </form>

    <h2>End of home page</h2>
</body>
</html>
