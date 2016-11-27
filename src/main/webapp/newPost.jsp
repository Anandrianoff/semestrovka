<%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 10.11.2016
  Time: 1:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создать статью</title>
</head>
<body>
    <form action="newPost" method=post id="message" class="form-control-static">
        <input type="text" name="header" id="header" class="form-control" placeholder="Заголовок" style="font-size: large"><br>
            <textarea name="comment" class="form-control" rows="20" cols="250" id="comment" placeholder="Текст" style="font-size: large"></textarea><br>
        <div class="row">
            <div class="col-md-6 col-sm-10 col-xs-12">
                <input type="submit" value="Готово" class="btn btn-lg btn-primary btn-block">
            </div>
        </div>
    </form>
</body>
</html>


