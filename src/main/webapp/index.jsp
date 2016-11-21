<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="i18n.UsersBundle"/>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Music forum</title>
</head>
<body>
<c:if test="${user!=null}">
    <c:if test="${user.role=='MUSICIAN'}">
        <form action="newPost" method="get">
            <input type="submit" value="Написать статью" class="btn btn-lg btn-primary btn-block">
        </form>
        <hr>
    </c:if>
</c:if>
    <c:forEach var="myitem" items="${posts}">
        <div class="row" >
            <div class="col-md-1"></div>
            <div class="col-md-10">
                <h3>${myitem.header}</h3><br>
                <p style="font-size: medium">${myitem.text}</p><br>
                <div class="row">
                    <div class="col-md-4">
                        <h5>Автор: ${myitem.author}</h5>
                    </div>
                    <div class="col-md-4">
                        <a id="comment${myitem.postId}"><h5 align="center">Комментарии</h5></a>
                    </div>
                    <div class="col-md-4">
                        <h5 align="right">Опубликовано: ${myitem.date}</h5>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-2"></div>
            <div class="col-md-8" id="comments${myitem.postId}" style=" height: 0px;  overflow-y: scroll; background-color: inherit"></div>
        </div>
        <hr color="#DCDCDC">
        <script>
            $(document).ready(function () {

                $('#comment${myitem.postId}').click(function () {

                    $.ajax({
                        url: "ServletGetComment",
                        method: "get",
                        data: {id:${myitem.postId}},
                        dataType: "text",
                        success:function (data) {
                            $('#comments${myitem.postId}').height(300);
                            $('#comments${myitem.postId}').html(data);
                        }
                    });
                })

            })
        </script>
    </c:forEach>

</body>
</html>
