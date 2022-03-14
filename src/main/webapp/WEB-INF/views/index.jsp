<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <script
            src="https://code.jquery.com/jquery-3.6.0.js"
            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
            crossorigin="anonymous">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
            integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <title>Accident</title>
</head>
<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">Номер</th>
        <th scope="col">Имя пользователя</th>
        <th scope="col">Текст заявления</th>
        <th scope="col">Адрес правонарушения</th>
        <th scope="col">Вид правонарушения</th>
        <th scope="col">Редактировать</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="acc" items="${accidents}">
        <tr>
            <td>${acc.key}</td>
            <td>${acc.value.name}</td>
            <td>${acc.value.text}</td>
            <td>${acc.value.address}</td>
            <td>${acc.value.accidentType.id}</td>
            <td><a href="<c:url value='/update?id=${acc.key}'/>">Изменить</a></td>
        </tr>
    </c:forEach>
    </tbody>
    <a href="<c:url value='/create'/>">Добавить инцидент</a>
</table>
</body>
</html>