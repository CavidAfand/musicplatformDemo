<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/25/2020
  Time: 3:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Search Result | ${user.name} ${user.surname}</title>
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
                    <form action="/listener/search_music" method="get">
                        <div class="input-group mb-3">
                            <input type="text" class="form-control" name="search" placeholder="Music search" required>
                            <div class="input-group-append">
                                <button type="submit" class="btn btn-primary"><i class="fas fa-search" style="font-size:15px;color:white;"></i></button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="music-content shadow p-4 mb-4 bg-white">

                    <span>Search result about <b>'${param.search}'</b></span><br>
                    <span><b>${resultList.size()}</b> songs found</span><br><br>

                    <c:forEach items="${resultList}" var="result">
                        <div class="card music-card shadow-sm p-4 mb-4 bg-white">
                            <div class="card-body">
                                <h2><span class="music-name">${result.key.musicName}</span></h2>
                                <c:if test="${result.key.musician != null}">
                                    <h3 class="music-artist">Author: <span><b><a href="/listener/musician/${result.key.musician.username}">${result.key.musician.name} ${result.key.musician.surname} (${result.key.musician.nickName})</a></b></span></h3>
                                </c:if>
                                <c:if test="${result.key.band != null}">
                                    <h3 class="music-artist">Author: <span><b><a href="/listener/band/${result.key.band.username}">${result.key.band.bandName}</a></b></span></h3>
                                </c:if>
                                <p><span class="music-genre">Genres: </span>
                                    <c:forEach items="${result.key.genres}" var="genre">
                                        <span><a href="/listener/genre/${genre.genre}">${genre.genre}</a></span>
                                    </c:forEach>
                                </p>
                                <p>Liked by ${result.key.listeners.size()} listeners</p>
                                <audio controls>
                                    <source src="${result.key.musicPath}">
                                </audio>
                                <c:if test="${result.value == true}">
                                    <form action="/listener/dislike_music" method="post">
                                        <input type="hidden" name="music" value="${result.key.musicPath}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <button type="submit" class="btn btn-danger btn-block">Dislike</button>
                                    </form>
                                </c:if>
                                <c:if test="${result.value != true}">
                                    <form action="/listener/like_music" method="post">
                                        <input type="hidden" name="music" value="${result.key.musicPath}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <button type="submit" class="btn btn-danger btn-block">Like</button>
                                    </form>
                                </c:if>
                            </div>
                        </div>

                    </c:forEach>

                </div>

            </div>

            <!-- right side -->
            <jsp:include page="components/right_side.jsp"></jsp:include>
            <!-- -->



        </div>
    </div>
</body>
</html>
