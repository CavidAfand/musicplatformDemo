<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 3/28/2020
  Time: 11:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="input" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Sign up | Band</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <link href='https://fonts.googleapis.com/css?family=Calligraffitti' rel='stylesheet'>
    <link rel="stylesheet" href="css/signup.css?dev=12">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-sm-3"></div>
            <div class="col-sm-6 signup-center">
                <h2 class="header">Music Platform</h2>
                <div class="signup-box">
                    <form:form action="/upload_band" method="post" enctype="multipart/form-data" modelAttribute="band">

                        <div class="form-group">
                            <label for="bandName">Band name<span class="req">*</span></label>
                            <form:input path="bandName" id="bandName" class="form-control" required="required"/>
                        </div>

                        <div class="form-group">
                            <label for="password">Password<span class="req">*</span></label>
                            <form:password path="password" id="password" class="form-control" required="required"/>
                        </div>

                        <div class="form-group">
                            <label for="date">Founded year<span class="req">*</span></label>
                            <input type="date" name="date" id="date" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="image">Image<span class="req">*</span></label>
                            <input type="file" name="image" id="image" class="form-control" required>
                        </div>

                        <div class="form-group">
                            <label for="code">Verification code<span class="req">*</span></label>
                            <input type="text" name="code" id="code" class="form-control" required>
                        </div>

                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <button type="submit" class="btn btn-warning btn-block">Sign up</button>

                    </form:form>
                </div>
            </div>
            <div class="col-sm-3"></div>
        </div>
    </div>
</body>
</html>
