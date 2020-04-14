<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/23/2020
  Time: 4:29 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Error - ${error}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
</head>
<body>
    <div class="jumbotron-fluid" style="text-align: center; margin-top: 15px; background-color: #7bd0da; padding: 15px;">
        <h3><b>Error: </b> ${error}</h3>
<%--        <a href="/" class="btn btn-primary">Go to Home page</a>--%>
        <c:choose>

            <c:when test="${type.equals(\"listener\")}">
                <a href="/listener/index" class="btn btn-primary">Go to Home page</a>
            </c:when>

            <c:when test="${type.equals(\"musician\")}">
                <a href="/musician/index" class="btn btn-primary">Go to Home page</a>
            </c:when>

            <c:when test="${type.equals(\"band\")}">
                <a href="/band/index" class="btn btn-primary">Go to Home page</a>
            </c:when>

            <c:otherwise>
                <a href="/" class="btn btn-primary">Go to Home page</a>
            </c:otherwise>

        </c:choose>
    </div>

</body>
</html>
