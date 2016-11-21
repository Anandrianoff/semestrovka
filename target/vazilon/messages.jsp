<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: Andrey
  Date: 05.11.2016
  Time: 21:40
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <LINK REL=STYLESHEET
          HREF="css/myStyles.css"
          TYPE="text/css">
    <title>Сообщения</title>
    <style type="text/css">
        .myMessage{
             background-color: aliceblue;
             width: 100%;
             height: 50px;
        }
        .friendMessage{
            background-color: antiquewhite;
            width: 100%;
            height: 50px;
        }
        .scroll{
            height:150px;
            overflow-y: scroll
        }
    </style>
</head>
<body>
<c:if test="${user==null}">
    <%response.sendRedirect("index.jsp");%>
</c:if>
<form method=post id="message" class="form-control-static">
<div class="row">
        <div class="col-md-3 col-sm-1"></div>
        <div class="col-md-6 col-sm-10 col-xs-12">
            <textarea name="comment" class="form-control" rows="5" cols="200" id="comment" placeholder="Сообщение.."></textarea><br>
        </div>
        <div class="col-md-3 col-sm-1"></div>
</div>
    <div class="row">
        <div class="col-md-3 col-sm-1"></div>
        <div class="col-md-3 col-sm-4 col-xs-12">
            <c:if test="${user.currentDialog.user1==user.username}">
                <h3>Диалог с ${user.currentDialog.user2}</h3>
            </c:if>
            <c:if test="${user.currentDialog.user2==user.username}">
                <h3>Диалог с ${user.currentDialog.user1}</h3>
            </c:if>
        </div>
        <div class="col-md-1 col-sm-2"></div>
        <div class="col-md-2 col-sm-4 col-xs-12">
            <input type="button" name="btn" id="btn" value="Отправить" class="btn btn-lg btn-primary btn-block">
        </div>
    </div>
</form>
<div class="row">
        <div class="col-md-3 col-sm-1"></div>
        <div class="col-md-6 col-sm-10 col-xs-12">
            <div style=" height: 500px; overflow-y: scroll;">
            <table class="table" id="table">
                <tbody id="tableb">
                <c:forEach var="myitem" items="${user.currentDialog.messages}">
                    <c:if test="${user.username==myitem.author}">
                        <tr class="active">
                            <td align="right"><p>${myitem.message}</p>${myitem.date}
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${user.username!=myitem.author}">
                        <tr class="success">
                            <td align="left"><p>${myitem.message}</p>${myitem.date}
                            </td>
                        </tr>
                    </c:if>
                </c:forEach>
                </tbody>
            </table>
            </div>
        </div>
        <div class="col-md-3 col-sm-1"></div>
    </div>
<script>
    var timestemp=<%=(new Date()).getTime()%>;
    $(document).ready(function () {
        $('#btn').click(function () {
            var msg_txt = $('#comment').val();
            if($.trim(msg_txt) != ""){
                $.ajax({
                    url: "ServletNewMessage",
                    method: "POST",
                    data: {text:msg_txt},
                    dataType: "text",
                    success:function (data) {
                        $('#comment').val("")
//                        window.location.reload(1);
//                        $('#comment').html(data+$('#comment').html());
//                            timestemp=new Date().getTime();
////                        itis2016_3
                    }
                });
            }
        })

        setInterval(function () {
            $.ajax({
                url: "ServletLoadNewMessages",
                method: "POST",
                data: {time:timestemp},
                success:function (data) {
                        $('#tableb').html(data+$('#tableb').html());
                            timestemp=new Date().getTime();
////                        itis2016_3

                }
            });
        }, 1000);
    })
</script>
</body>
</html>

