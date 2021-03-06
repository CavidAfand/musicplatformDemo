<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/29/2020
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Home | ${user.name} ${user.surname}</title>
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
<body onload="${action}()">

    <!-- navbar -->
    <jsp:include page="components/navbar.jsp"></jsp:include>
    <!-- -->

    <div class="container-fluid">
        <div class="row">

            <!-- left side -->
            <jsp:include page="components/left_side.jsp"></jsp:include>
            <!-- -->

            <!-- Personal information -->
            <div class="col-sm-4">
                <div class="personal-information shadow p-4 mb-4 bg-white">
                    <h2>Personal information</h2>
                    <hr>
                    <div class="information">
                        <p><span class="info-head"><b>Gender: </b></span><span class="info-content">${user.gender}</span></p>
                        <p><span class="info-head"><b>Date: </b></span><span class="info-content">${user.date}</span></p>
                        <p><span class="info-head"><b>Email: </b></span><span class="info-content">${user.email}</span></p>
                        <p><span class="info-head"><b>Username: </b></span><span class="info-content">${user.username}</span></p>
                    </div>
                </div>
            </div>
            <!-- -->

            <!-- Statistical information -->
            <div class="col-sm-3">
                <div class="statistic-information shadow p-4 mb-4 bg-white">
                    <h2>Statistical information</h2>
                    <hr>
                    <p><span class="info-head"><b>Number of your liked musics:</b> </span><span class="info-content">${user.likedMusics.size()}</span></p>
                    <p><span class="info-head"><b>Number of your liked artists:</b> </span><span class="info-content">${user.musicians.size() + user.bands.size()}</span></p>
                </div>
            </div>
            <!-- -->

            <!-- right side -->
            <jsp:include page="components/right_side.jsp"></jsp:include>
            <!-- -->

        </div>
    </div>

</body>
</html>
