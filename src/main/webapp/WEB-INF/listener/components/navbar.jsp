<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/31/2020
  Time: 10:27 PM
  To change this template use File | Settings | File Templates.
--%>

<!-- navbar -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand"  href="/">Music Platform</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarDemo" aria-controls="navbarDemo" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarDemo">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

            <li class="nav-item ">
                <a class="nav-link" href="/listener/all_music" ><b>Songs</b></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/listener/all_posts"><b>Posts</b></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/listener/musicians"><b>Musicians</b></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/listener/bands"><b>Bands</b></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" data-toggle="modal" data-target="#musicGenres">Genres</a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarSetting" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Setting
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarSetting">
                    <a class="dropdown-item" data-toggle="modal" data-target="#passwordModal">Change password</a>
                    <a class="dropdown-item" data-toggle="modal" data-target="#editModal">Edit information</a>
                </div>
            </li>

        </ul>
    </div>
</nav>
<!-- -->

<!-- Modals -->

    <!-- Music genres modal -->
    <div class="modal" id="musicGenres">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal header -->
                <div class="modal-header">
                    <h4 class="modal-title">Select your favourite genres to see songs</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- -->

                <!-- -->
                <div class="modal-body">
                    <div class="card-columns">
                        <c:forEach items="${genres}" var="genre">
                            <div class="card bg-light">
                                <a href="/listener/genre/${genre}" class="card-link">
                                    <div class="card-body text-center">
                                        <p class="card-text">${genre}</p>
                                    </div>
                                </a>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- -->
            </div>
        </div>
    </div>
    <!-- -->

    <!-- Password change modal -->
    <div class="modal" id="passwordModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal header -->
                <div class="modal-header">
                    <h4 class="modal-title">Change password</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- -->

                <!-- Modal body -->
                <div class="modal-body">
                    <form action="/listener/change_password" method="post">

                        <div class="form-group">
                            <label for="currentPass">Current password</label>
                            <input type="password" name="currentPassword" id="currentPass" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="newPass">New password</label>
                            <input type="password" name="newPassword" id="newPass" class="form-control" required>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                        <button type="submit" class="btn btn-warning btn-block">Change password</button>

                    </form>
                </div>
                <!-- -->

            </div>
        </div>
    </div>
    <!-- -->

    <!-- Edit information modal -->
    <div class="modal" id="editModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <!-- Modal header -->
                <div class="modal-header">
                    <h4 class="modal-title">Edit information</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <!-- Modal content -->
                <div class="modal-body">
                    <form action="/listener/edit_information" method="post">

                        <div class="form-group">
                            <label for="edit_name">Name</label>
                            <input type="text" name="name" id="edit_name" class="form-control" value="${user.name}" required>
                        </div>

                        <div class="form-group">
                            <label for="edit_surname">Surname</label>
                            <input type="text" name="surname" id="edit_surname" class="form-control" value="${user.surname}" required>
                        </div>

                        <div class="form-group">
                            <label for="edit_date">Date</label>
                            <input type="date" name="date" id="edit_date" class="form-control" value="${user.date}" required>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                        <button type="submit" class="btn btn-warning btn-block">Edit</button>

                    </form>
                </div>

            </div>
        </div>
    </div>
    <!-- -->
<!-- -->