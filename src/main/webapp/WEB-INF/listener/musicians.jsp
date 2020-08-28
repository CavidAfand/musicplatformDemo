<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/26/2020
  Time: 5:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Musicians | ${user.name} ${user.surname}</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
    <link rel="stylesheet" href="../css/home.css?dev=14">
    <link href='https://fonts.googleapis.com/css?family=Aladin' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Amaranth' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Aref Ruqaa' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Belgrano' rel='stylesheet'>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <script src="../js/home_action.js"></script>
</head>
<body>

    <!-- navbar -->
    <jsp:include page="components/navbar.jsp"></jsp:include>
    <!-- -->

    <div class="container-fluid">
        <div class="row">

            <!-- left side -->
            <jsp:include page="components/left_side.jsp"></jsp:include>
            <!-- -->

            <div class="col-sm-7">

                <div class="music-content shadow p-4 mb-4 bg-white">
                    <form action="/listener/search_artist" method="get">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="search" placeholder="Search musician" required>
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-primary"><i class="fas fa-search" style="font-size:15px;color:white;"></i></button>
                            </div>
                        </div>
                    </form>
                </div>


                <div class="music-content shadow p-4 mb-4 bg-white">

                    <h3 class="content-header">Artists you can like</h3>

                    <div class="row">
                        <c:forEach items="${musicians}" var="musician">
                            <div class="col-sm-6">
                                <div class="card music-card shadow-sm p-4 mb-4 bg-white">
                                    <img class="card-img-top" src="${musician.imagePath}" alt="profile image" style="width:100%">
                                    <div class="card-body">
                                        <h4 class="card-title">${musician.name} ${musician.surname} (${musician.username})</h4>
                                        <a href="/listener/musician/${musician.username}/songs" class="btn btn-warning btn-block">See Songs</a>
                                        <a href="/listener/musician/${musician.username}/posts" class="btn btn-warning btn-block">See Posts</a>
                                    </div>
                                </div>
                            </div>

                        </c:forEach>

                    </div>

                    <!-- Pagination -->
                    <jsp:include page="components/musician_pagination.jsp"></jsp:include>
                    <!-- -->
                </div>
            </div>

            <!-- right side -->
            <jsp:include page="components/right_side.jsp"></jsp:include>
            <!-- -->

        </div>
    </div>

</body>
</html>
