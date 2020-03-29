<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/25/2020
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="/login_musician" method="post">
        <label for="email">Email: </label>
        <input type="text" name="username" id="email"><br><br>

        <label for="password">Password: </label>
        <input type="password" name="password" id="password"><br><br>

        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit">Sign in</button>
    </form>
</body>
</html>
