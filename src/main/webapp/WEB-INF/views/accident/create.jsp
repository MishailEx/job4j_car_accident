<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        <%@include file='../css/style.css' %>
    </style>
</head>
<body>
<a href="/" class="btn">На главную</a>
<a style="float: right" href="/logout" class="btn">Выход</a>
<form class="formEdit" style="width: 60%; height: 80%" action="<c:url value='/save'/>" method="POST" enctype="multipart/form-data">
    <h3 style="text-align: center">Новый инцедент</h3>
    <div style="width: 100%;" class="editing">
        <div style="width: 100%; height: 80px; display: block">
            <div style="width: 50%; height: 100%; display: inline;">
                <div style="margin: 0 auto; float: none; text-align: center">Тема обращния:</div>
                <input type='text' name='name'>
            </div>
            <div style="display: inline; float: right; height: 100%; width: 45%">
                <div style="margin: 0 auto; float: none; text-align: center">Вид правонарушения</div>
                <select name='type.id'>
                    <c:forEach var="type" items="${types}">
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div style="width: 100%; height: 130px; display: block">
            <div style="display: inline; height: 100%; width: 50%">
                <div style="margin: 0 auto; float: none; text-align: center">Описание:</div>
                <textarea style="height: 95px" type='text' name='text'></textarea>
            </div>
            <div style="display: inline; float: right; width: 45%; height: 100%; visibility: hidden" >
                <div style="margin: 0 auto; float: none; text-align: center">Статьи:</div>
                <select name="rIds" multiple>
                    <c:forEach var="rule" items="${rules}">
                        <c:if test="${rule.id == 1}">
                            <option selected value="${rule.id}">${rule.name}</option>
                        </c:if>
                        <c:if test="${rule.id != 1}">
                            <option value="${rule.id}">${rule.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div style="width: 100%; height: 80px; display: block;">
            <div style="width: 50%; height: 100%">
                <div style="margin: 0 auto; float: none; text-align: center">Адресс правонарушения:</div>
                <input type='text' name='address'>
            </div>
            <div style="display: inline; float: right; height: 100%; width: 45%">
                <div style="margin: 0 auto; float: none; text-align: center">Загрузите фотографии</div>
                <input class="button" type="file" name="file" multiple>
            </div>
        </div>
    </div>
    <div>
        <div style="float: right; margin: 20px;" colspan='2'>
            <input class="button" name="submit" type="submit" value="Сохранить"/>
        </div>
    </div>
</form>
</body>
</html>