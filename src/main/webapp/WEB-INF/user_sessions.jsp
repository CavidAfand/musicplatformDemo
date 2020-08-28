<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/30/2020
  Time: 10:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <p>Number of active user: ${list.size()}</p>
    <ul>
        <c:forEach items="${list}" var="li">
            <li>${li}</li>
        </c:forEach>
    </ul>

</body>
</html>
