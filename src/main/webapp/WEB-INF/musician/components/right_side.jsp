<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 4/8/2020
  Time: 12:16 AM
  To change this template use File | Settings | File Templates.
--%>

<div class="col-sm-2 right-side">
    <div class="shadow p-4 mb-4 bg-white">
        <form action="/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn btn-danger btn-block logout"><i class='fas fa-door-open' style='font-size:18px'></i> Log out</button>
        </form>
    </div>
</div>
