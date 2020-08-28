<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/16/2020
  Time: 5:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Posts | ${user.name} ${user.surname}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
    <link rel="stylesheet" href="../css/home.css?dev=14">
    <link href='https://fonts.googleapis.com/css?family=Aladin' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Amaranth' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Aref Ruqaa' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Bellazo' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <script src="../js/home_action.js"></script>
</head>
<body>

    <!-- navbar -->
    <jsp:include page="components/navbar.jsp"/>
    <!-- -->

    <div class="container-fluid">
        <div class="row">

            <!-- General information and images (left side) -->
            <jsp:include page="components/left_side.jsp"/>
            <!-- -->

            <div class="col-sm-7">
                <div class="post-content shadow p-4 mb-4 bg-white">
                    <h2 class="info-head" style="text-align: center;">Posts</h2>
                    <c:forEach items="${postList}" var="post">
                        <div class="card post-card shadow-sm p-4 mb-4 bg-white">
                            <div class="card-body">
                                <p class="card-text">${post.text}</p>
                                <p class="card-text"><span class="info-head">Release date: </span><span class="info-content">${post.date}</span></p>
                                <c:if test="${post.imagePath != null}">
                                    <div>
                                        <img src="${post.imagePath}" class="img-fluid mx-auto d-block img-thumbnail post-image">
                                    </div>
                                </c:if>
                                <form action="/musician/delete_post" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" name="post" value="${post.imagePath}">
                                    <button type="submit" class="btn btn-danger btn-block">Delete</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>

                    <!-- Pagination -->
                    <jsp:include page="components/post_pagination.jsp"></jsp:include>
                    <!-- --->
                </div>
            </div>

            <!-- Log out part (right side) -->
            <jsp:include page="components/right_side.jsp"/>
            <!-- -->

        </div>
    </div>
</body>
</html>
