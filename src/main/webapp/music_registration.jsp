<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 26.10.2016
  Time: 23:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="reg" method=post id="register-form" class="form-signin">
    <input type="text" name="email" id="email" size="25" class="form-control" placeholder="e-mail"><br>
    <input type="text" name="username" id="username" size="25" class="form-control" placeholder="Username"><br>
    <input type="text" name="firstname" id="firstname" size="25" class="form-control" placeholder="Name"><br>
    <input type="text" name="lastname" id="lastname" size="25" class="form-control" placeholder="lastname"><br>
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
    <input type="password" name="password" id="password" size="25" class="form-control" placeholder="password"><br>
    <input type="submit" value="Регистрация" class="btn btn-lg btn-primary btn-block">
</form>
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
        $("#register-form").validate({
            onkeyup: false,
            // Specify the validation rules
            rules: {
                username: {
                    cache: false,
                    required: true,
                    //minlength: 5,
                    remote: {
                        url: "checkusername",
                        type: "post"
                    }
                },
                email: {
                    required: true,
                    email: true

                },
                password: {
                    required: true,
                    minlength: 5
                },
                agree: "required"
            },

            // Specify the validation error messages
            messages: {
                firstname: "Ошибка",
                lastname: "Ошибка",
                password: {
                    required: "Это обязательное поле",
                    minlength: "Минимальная длина 5"
                },
                username: {
                    required: "Это обязательное поле",
                    minlength: "Введите что-нибудь подлиннее",
                    remote: "Занято"
                },
                email: "Ошибка",
                agree: "Согласитеесь"
            },

            submitHandler: function (form) {

                form.submit();

            }
        });
        $("#register-form").change(function () {
            $("#username").removeData("previousValue"); //clear cache when changing group
            $("#register-form").data('validator').element("#username"); //retrigger remote call
            //my validator is stored in .data() on the form
        });


    });


    </script>

</body>
</html>
