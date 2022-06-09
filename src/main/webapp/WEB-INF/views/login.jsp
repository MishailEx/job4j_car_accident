<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <style>
        <%@include file='css/style.css' %>
    </style>
</head>
<body>
<c:if test="${not empty errorMessage}">
    <div style="color:red; font-weight: bold; margin: 30px 0px; position: absolute">
            ${errorMessage}
    </div>
</c:if>

<form class="modalForm" name='login' action="<c:url value='/login'/>" method='POST'>
    <div class=containerHead>
        <h2 style="position: absolute">Login</h2>
    </div>
    <div class="text-field">
        <input class="text-field__input" type="text" name="username" id="username" placeholder="Username">
    </div>
    <div class="text-field">
        <input class="text-field__input" type="password" name="password" id="password" placeholder="Password">
    </div>
    <input class="button" name="submit" type="submit" value="Submit"/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <a class="button" href="<c:url value='/reg'/>">Registration</a>
</form>
</body>
</html>