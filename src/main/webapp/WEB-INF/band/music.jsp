<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/16/2020
  Time: 9:41 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Musics | ${user.bandName}</title>
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

                <div class="music-content shadow p-4 mb-4 bg-white">
                    <form action="/band/search_music" method="post">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="search" placeholder="Music search" required>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-primary"><i class="fas fa-search" style="font-size:15px;color:white;"></i></button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="music-content shadow p-4 mb-4 bg-white">
                    <c:choose>
                        <c:when test="${search == \"true\"}">
                            <p>Search Result: </p>
                            <c:forEach items="${searchList}" var="slist">
                                <div class="card music-card shadow-sm p-4 mb-4 bg-white">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-9"><h3 class="card-title"><span class="music-name">${slist.musicName}</span></h3></div>
                                            <div class="col-sm-3">
                                                <form action="/band/delete_music" method="post">
                                                    <input type="hidden" name="music" value="${slist.musicPath}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    <button type="submit" class="btn btn-danger btn-block">Delete</button>
                                                </form>
                                            </div>
                                        </div>
                                        <p class="card-text"><span class="info-head"><b>Info:</b> </span><span class="info-content">${slist.information}</span></p>
                                        <p class="card-text"><span class="info-head"><b>Release date:</b></span> <span class="info-content">${slist.releaseDate}</span></p>
                                        <p class="card-text"><span class="info-head"><b>Genres:</b></span>
                                            <c:forEach items="${slist.genres}" var="sgenre">
                                                <span class="info-content">${sgenre.genre}</span>
                                            </c:forEach>
                                        </p>
                                        <audio controls>
                                            <source src="${slist.musicPath}">
                                        </audio>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <c:forEach items="${musicList}" var="list">
                                <div class="card music-card shadow-sm p-4 mb-4 bg-white">
                                    <div class="card-body">
                                        <div class="row">
                                            <div class="col-sm-9"><h3 class="card-title"><span class="music-name">${list.musicName}</span></h3></div>
                                            <div class="col-sm-3">
                                                <form action="/band/delete_music" method="post">
                                                    <input type="hidden" name="music" value="${list.musicPath}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    <button type="submit" class="btn btn-danger btn-block">Delete</button>
                                                </form>
                                            </div>
                                        </div>
                                        <p class="card-text"><span class="info-head"><b>Info:</b> </span><span class="info-content">${list.information}</span></p>
                                        <p class="card-text"><span class="info-head"><b>Release date:</b></span> <span class="info-content">${list.releaseDate}</span></p>
                                        <p class="card-text"><span class="info-head"><b>Genres:</b></span>
                                            <c:forEach items="${list.genres}" var="genre">
                                                <span class="info-content">${genre.genre}</span>
                                            </c:forEach>
                                        </p>
                                        <audio controls>
                                            <source src="${list.musicPath}">
                                        </audio>
                                    </div>
                                </div>
                            </c:forEach>

                            <!-- pagination begin -->
                            <jsp:include page="components/music_pagination.jsp"></jsp:include>
                            <!-- pagination end -->

                        </c:otherwise>
                    </c:choose>
                </div>

            </div>

            <!-- Log out part (right side) -->
            <jsp:include page="components/right_side.jsp"/>
            <!-- -->
        </div>
    </div>

</body>
</html>
