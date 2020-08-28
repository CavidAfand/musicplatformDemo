<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/29/2020
  Time: 6:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>${musician.name} ${musician.surname}'s songs | ${user.name} ${user.surname}</title>
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

                    <h3 class="content-header">${musician.name} ${musician.surname} (${musician.nickName})'s songs</h3>

                    <c:forEach var="music" items="${musicList}">
                        <div class="card music-card shadow-sm p-4 mb-4 bg-white">
                            <div class="card-body">
                                <h2><span class="music-name">${music.musicName}</span></h2>
                                <h3 class="music-artist">Author: <span><b><a href="/listener/musician/${music.musician.username}">${music.musician.name} ${music.musician.surname} (${music.musician.nickName})</a></b></span></h3>
                                <p><span class="music-genre">Genres: </span>
                                    <c:forEach items="${music.genres}" var="genre">
                                        <span><a href="/listener/genre/${genre.genre}">${genre.genre}</a></span>
                                    </c:forEach>
                                </p>
                                <p>Liked by ${music.listeners.size()} listeners</p>
                                <audio controls>
                                    <source src="${music.musicPath}">
                                </audio>
                            </div>
                        </div>
                    </c:forEach>

                    <!-- Pagination -->
                    <jsp:include page="components/musicianSong_pagination.jsp"></jsp:include>
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
