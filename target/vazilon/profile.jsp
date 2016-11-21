<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 12.11.2016
  Time: 15:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Личный кабинет</title>
</head>
<body>
<form action="profile" method=post id="myForm" class="form-signin">
<%--<input type="text" name="username" id="username" size="25" class="form-control" placeholder="new username"><br>--%>
<div class="radio">
    <label>
        <input type="radio" name="status" id="statusM" value="musician" checked>
        Я &mdash; музыкант
    </label>
    <label >
        <input type="radio" name="status" id="statusR" value="reader">
        Я &mdash; простой смертный
    </label>
</div>
<input type="password" name="oldpassword" id="oldpassword" size="25" class="form-control" placeholder="old password"><br>
<input type="password" name="password" id="password" size="25" class="form-control" placeholder="password"><br>
<input type="submit" value="Готово" class="btn btn-lg btn-primary btn-block">
</form>
<script src="webjars/jquery-validation/1.14.0/jquery.validate.min.js"></script>
<script>

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
    $(function () {
        $("#myForm").validate({
            onkeyup: true,
            // Specify the validation rules
            rules: {
                oldpassword: {
                    required: true,
                    remote: {
                        url: "checkpassword",
                        type: "post"
                    }
                },
                password: {
                    required: true,
                    minlength: 5
                },
                agree: "required"
            },

            // Specify the validation error messages
            messages: {
                oldpassword: {
                    required: "Это обязательное поле",
                    remote: "Неверный пароль"
                },
                password: {
                    required: "Обязательное поле",
                    minlength: "Минимальная длина 5"
                },
                agree: "Согласитеесь"
            },

            submitHandler: function (form) {

                form.submit();

            }
        });
        $("#myForm").change(function () {
            $("#oldpassword").removeData("previousValue"); //clear cache when changing group
            $("#myForm").data('validator').element("#oldpassword"); //retrigger remote call
            //my validator is stored in .data() on the form
        });

    });
</script>
</body>
</html>
