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
    <a class="navbar-brand"  href="/band/index">Music Platform</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse"
            data-target="#navbarDemo" aria-controls="navbarDemo" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarDemo">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">

            <li class="nav-item ">
                <a class="nav-link" href="/band/music" ><b>Musics</b></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="/band/post"><b>Posts</b></a>
            </li>

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarShare" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Share
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarShare">
                    <a class="dropdown-item" data-toggle="modal" data-target="#musicModal">Music</a>
                    <a class="dropdown-item" data-toggle="modal" data-target="#postModal">Post</a>
                </div>
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

    <!-- Music modal -->
    <div class="modal" id="musicModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- modal header -->
                <div class="modal-header">
                    <h4 class="modal-title">Upload music file</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- -->

                <!-- modal body -->
                <div class="modal-body">
                    <form action="/band/upload_music" method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <label for="name">Music name</label>
                            <input type="text" name="name" id="name" placeholder="Name" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="info">Additional information</label>
                            <textarea name="information" id="info" class="form-control"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="musicFile">Music</label>
                            <input type="file" name="musicFile" id="musicFile" class="form-control" required>
                        </div>

                        <div>
                            <span>Genres:</span><br>
                            <c:forEach items="${genres}" var="genre">
                                <div class="form-check-inline">
                                    <label class="form-check-label" for="${genre}">
                                        <input type="checkbox" class="form-check-input" id="${genre}" name="genre" value="${genre}">${genre}
                                    </label>
                                </div>
                            </c:forEach>
                        </div>
                        <br>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                        <button type="submit" class="btn btn-success btn-block">Upload</button>

                    </form>
                </div>
                <!-- -->

            </div>
        </div>
    </div>
    <!-- -->

    <!-- Post modal -->
    <div class="modal" id="postModal">
        <div class="modal-dialog">
            <div class="modal-content">

                <div class="modal-header">
                    <h4 class="modal-title">Create new post</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>

                <div class="modal-body">
                    <form action="/band/upload_post" method="post" enctype="multipart/form-data">

                        <div class="form-group">
                            <label for="text">Text</label>
                            <textarea name="text" id="text" class="form-control"></textarea>
                        </div>

                        <div class="form-group">
                            <label for="image">Image</label>
                            <input type="file" name="image" id="image" class="form-control">
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                        <button type="submit" class="btn btn-success btn-block">Create post</button>

                    </form>
                </div>

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
                    <form action="/band/change_password" method="post">

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
                    <form action="/band/edit_information" method="post">

                        <div class="form-group">
                            <label for="edit_name">Name</label>
                            <input type="text" name="name" id="edit_name" class="form-control" value="${user.bandName}" required>
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