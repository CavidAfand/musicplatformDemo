<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/7/2020
  Time: 11:54 PM
  To change this template use File | Settings | File Templates.
--%>

<div class="col-sm-3">
    <div class="left-side shadow p-4 mb-4 bg-white">

        <!-- Image -->
        <div class="image-container">
            <img src="${user.imagePath}" class="img-fluid mx-auto d-block img-thumbnail">
            <button type="button" class="btn btn-primary btn-block chImage" data-toggle="modal" data-target="#changeImage">Change photo</button>
        </div>
        <!-- -->

        <hr>

        <!-- General info -->
        <div class="information">
            <p><span class="info-head"><b>Name: </b></span><span class="info-content">${user.name}</span></p>
            <p><span class="info-head"><b>Surname: </b></span><span class="info-content">${user.surname}</span></p>
        </div>
        <!-- -->

        <hr>

        <!-- Listener links -->
        <div class="listener-links">
            <a href="/listener/liked_music" class="btn btn-dark btn-block">Liked Songs</a>
            <a href="/listener/liked_artists" class="btn btn-dark btn-block">Liked Artists</a>
        </div>
        <!-- -->

    </div>
</div>


<!-- Image change modal -->
<div class="modal" id="changeImage">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal header -->
            <div class="modal-header">
                <h4 class="modal-title">Change image</h4>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
            <!-- -->

            <!-- Modal body -->
            <div class="modal-body">
                <form action="/listener/change_image" method="post" enctype="multipart/form-data">
                    <div class="form-group">
                        <label for="changedImage">Image</label>
                        <input type="file" name="image" id="changedImage" class="form-control" required>
                    </div>

                    <input type="hidden" name="original_name" value="${user.imagePath}">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">

                    <button type="submit" class="btn btn-warning btn-block">Change</button>
                </form>
            </div>
            <!-- -->

        </div>
    </div>
</div>
<!-- -->
