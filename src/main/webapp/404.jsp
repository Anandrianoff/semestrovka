<!DOCTYPE html>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator"
           prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.UsersBundle"/>


<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="favicon.ico">

    <title>404 page not fount</title>

    <!-- Bootstrap core CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/jumboron.css" rel="stylesheet">

    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <script src="https://code.jquery.com/ui/1.11.3/jquery-ui.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.3/i18n/jquery-ui-i18n.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <link href="https://code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


    <![endif]-->
</head>

<body>

<!--
<nav class="navbar navbar-inverse">

</nav>-->

<nav class="navbar navbar-inverse navbar-fixed-top">
    <div>
        <div class="container-fluid">
            <div>
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#" class="navbar-brand">Music forum</a></li>
                </ul>
            </div>

            <c:if test="${user!=null}">
                <a class="navbar-brand navbar-right"> ${user.firstname} ${user.lastname}</a>
            </c:if>
            <c:if test="${user==null}">


                <form class="navbar-form navbar-right" action="login" method=post>
                    <div class="form-group">
                        <input type="text" placeholder="Email" class="form-control" name="j_username" size="25">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control" size="15" name="j_password">
                    </div>
                    <input type="hidden" name="url" value="<%= request.getRequestURI() %>">
                    <button type="submit" class="btn btn-success"><fmt:message key="header.label.signin"/></button>
                    <a href="register" class="btn btn-success"><fmt:message key="header.label.register"/></a>
                </form>
            </c:if>

            <!--/.navbar-collapse -->
            <form id="languages" class="navbar-form navbar-right">
                <div class="form-group">
                    <a href="logout" class="btn btn-success"><fmt:message key="header.label.logout"/></a>
                </div>
            </form>
        </div>
    </div>
</nav>
<div class="jumbotron">
    <div class="container">
        404 page not found


    </div>
</div>

<div class="container">
    <!-- Example row of columns -->
    <div class="row">
        <div class="col-md-4">
            <h2>О проекте</h2>

            <p>Проект создан для повышения музыкальной грамотности абсолютно всех </p>

            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-4">
            <h2>Авторы</h2>

            <p>Авторами всех постов являются только лишь музыканты-пользователи этого ресурса</p>

            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-4">
            <h2>Посетителям</h2>

            <p>Узнавайте что-то новое и развивайтесь</p>

            <p><a class="btn btn-default" href="#" role="button">View details &raquo;</a></p>
        </div>
    </div>

    <hr>

    <footer>
        <p>&copy; Mu 2015</p>
        <script>
            $(function () {
                $.datepicker.setDefaults(
                        $.extend($.datepicker.regional["${language}"]));
                $(".datepicker").datepicker({
                    minDate: null,
                    maxDate: null, dateFormat: 'yy-mm-dd' }).val();;
            });
        </script>
    </footer>
</div>
<!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
</body>
</html>


