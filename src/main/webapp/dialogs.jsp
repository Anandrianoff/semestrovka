<%@ page import="tatar.tourism.pojo.User" %>
<%@ page import="tatar.tourism.dao.MySQLDaoFactory" %>
<%@ page import="tatar.tourism.pojo.Dialog" %><%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 03.11.2016
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="myStyles.css" rel="stylesheet" type="text/css" />

    <title>Диалоги</title>
</head>
<body>

    <c:if test="${user==null}">
        <%response.sendRedirect("index.jsp");%>
    </c:if>
    <form action="ServletCreateDialog" method=post id="create-dial-form" class="form-signin">
        <input type="text" name="username" id="username" size="25" class="form-control" placeholder="Введите логин собеседника"><br>
        <input type="submit" value="Создать диалог" class="btn btn-lg btn-primary btn-block">
    </form>
    <table class="table table-hover">
        <thead>
        <tr>
            <th>Список диалогов</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="myitem" items="${user.dialogs}">
                <tr onclick="window.location.href='messages?dialog=${myitem.id}'; return false">
                    <c:if test="${user.username==myitem.user1}">
                            <td>Диалог с ${myitem.user2}</td>
                    </c:if>
                    <c:if test="${user.username==myitem.user2}">
                        <td>Диалог с ${myitem.user1}</td>
                    </c:if>
                </tr>

        </c:forEach>
        </tbody>
    </table>
    <script src="webjars/jquery-validation/1.14.0/jquery.validate.min.js"></script>
    <script>// jQuery Form Validation code -->
    $.validator.setDefaults({
        highlight: function (element) {
            $(element).closest('.form-group').addClass('has-error');
        },
        unhighlight: function (element) {
            $(element).closest('.form-group').removeClass('has-error');
        },
        errorElement: 'span',
        errorClass: 'help-block',
        errorPlacement: function (error, element) {
            if (element.parent('.input-group').length) {
                error.insertAfter(element.parent());
            } else {
                error.insertAfter(element);
            }
        }
    });

    // When the browser is ready...
    $(function () {

        // Setup form validation on the #register-form element
        $("#create-dial-form").validate({
            onkeyup: false,
            // Specify the validation rules
            rules: {
                username: {
                    cache: false,
                    remote: {
                        url: "ServletCheckUsernameAndDialog",
                        type: "post"
                    }
                },
                agree: "required"
            },

            // Specify the validation error messages
            messages: {

                username: {
                    remote: "Такого пользователя не существует или у вас есть с ним диалог"
                }
            },

            submitHandler: function (form) {

                form.submit();

            }
        });
    });


    </script>
</body>
</html>
