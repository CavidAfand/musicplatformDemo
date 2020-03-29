<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/25/2020
  Time: 7:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Successful registration</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
    <div class="jumbotron-fluid" style="text-align: center; margin-top: 15px; background-color: #4b73da; padding: 15px;">
    <h3 style="color: white"><b>Congratulations!</b> You have successfully registered.</h3>
        <p>Your username: <span style="color:red;"><b><c:out value="${param.username}"></c:out></b></span></p>
    <p style="color:white;">Please, sign in platform with username and enjoy.</p>
    <a href="/" class="btn btn-success">Go to Home page</a>
    </div>
</body>
</html>
