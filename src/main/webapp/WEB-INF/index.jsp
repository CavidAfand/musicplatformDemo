<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/17/2020
  Time: 5:19 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

    <h2><c:out value="${param.platformUser.getName()}"></c:out></h2>
    <h2><c:out value="${param.username}"></c:out></h2>

    <h2>Test: <% out.println(request.getSession().getAttribute("platformUser")); %></h2>

    <c:if test="${param.platformUser == \"listener\"}">
        <p>If serti isledi</p>
    </c:if>

    <p>Platform: ${platformUser}</p>
    <p>Password: ${user.password}</p>
    <p>Email: ${user.email}</p>

    <form action="/logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <button type="submit">Log out</button>
    </form>

    <h1>Home page call: </h1>
    <jsp:include page="index_pages/index_listener.jsp"/>
</body>
</html>
