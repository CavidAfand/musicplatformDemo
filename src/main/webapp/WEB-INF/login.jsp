<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/16/2020
  Time: 2:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
    <title>Login Page</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/log.css?dev=3">
    <link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Artifika' rel='stylesheet'>
    <link href='https://fonts.googleapis.com/css?family=Farsan' rel='stylesheet'>
    <script src='https://kit.fontawesome.com/a076d05399.js'></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container-fluid">
        <div class="row">

            <!-- left part begin-->
            <div class="col-md-6 jumbolog">
                <div class="jumbotron">
                    <div class="horizon">
                        <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-3"></div>
                            <div class="col-md-9">
                                <div class="points">
                                    <h1><strong>Music Platform</strong></h1>
                                    <p><i class="fas fa-heart" style="color:white"></i> &nbsp;&nbsp;Listen your lovely songs</p>
                                    <p><i class="fas fa-users" style="color:white"></i>  &nbsp;&nbsp;Follow artists' activities</p>
                                    <p><i class="fas fa-music" style="color:white"></i>  &nbsp;&nbsp;Add your songs</p>
                                </div>
                            </div>
                        </div>
                    </div>
                    </div>

                </div>
            </div>
            <!-- left part end-->

            <!-- right part begin -->
            <div class="col-md-6">
                <div class="container">
                    <div class="row">
                        <div class="col-md-2"></div>
                        <div class="col-md-8">
                            <div class="loginbox">
                                <h2>Welcome to music world!</h2>
                                <c:if test="${param.error != null}">
                                    <p class="error-message"><strong>Your email or password is wrong.</strong></p>
                                </c:if>

                                <!-- login part -->
                                <form action="/login" method="post">
                                    <div class="form-group">
                                        <label for="username">Username</label>
                                        <input type="text" name="username" id="username" class="form-control" placeholder="Username" required>
                                    </div>

                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input type="password" name="password" id="password" class="form-control" placeholder="Password" required>
                                    </div>

                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <button type="submit" class="btn btn-primary btn-block">Sign in</button>

                                </form>
                                <a href="#" class="">Forgot password?</a>
                            <!-- login part end -->
                            </div>

                            <div class="signupbox">
                                <h2>Don't have an account?</h2>
                                <h3>Sign up as</h3>
                                <a type="button" data-toggle="modal" data-target="#listenerModal" class="btn">Listener</a>
                                <a type="button" data-toggle="modal" data-target="#musicianModal" class="btn">Singer or Musician</a>
                                <a type="button" data-toggle="modal" data-target="#bandModal" class="btn">Band</a>
                            </div>
                        </div>
                        <div class="col-md-2"></div>
                    </div>
                </div>
            </div>
            <!-- right part end -->

        </div>
    </div>

    <!-- Listener form begin -->
    <div class="modal fade" id="listenerModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Sign up as listener</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/signup_listener" method="post">
                        <div class="form-group">
                            <label for="lemail">Email</label>
                            <input type="email" name="email" id="lemail" class="form-control" required>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger btn-block">Sign Up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Listener form end -->

    <!-- Musician form begin -->
    <div class="modal fade" id="musicianModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Sign up as musician or singer</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/signup_musician" method="post">
                        <div class="form-group">
                            <label for="memail"></label>
                            <input type="email" name="email" id="memail" class="form-control" required>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger btn-block">Sign up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Musician form end -->

    <!-- Band form begin -->
    <div class="modal fade" id="bandModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Sign up as band</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/signup_band" method="post">
                        <div class="form-group">
                            <label for="bemail"></label>
                            <input type="email" name="email" id="bemail" class="form-control" required>
                        </div>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-danger btn-block">Sign up</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- Band form end -->


</body>
</html>
