<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<body>
<form action="<c:url value='/save'/>" method='POST'>
    <table>
        <tr>
            <td>Имя:</td>
            <td><input type='text' name='name'></td>
        </tr>
        <tr>
            <td>Описание инцидента:</td>
            <td><input type='text' name='text'></td>
        </tr>
        <tr>
            <td>Адресс:</td>
            <td><input type='text' name='address'></td>
        </tr>
            <td>Вид правонарушения</td>
            <td>
                <select name='type.id'>
                    <c:forEach var="type" items="${types}" >
                        <option value="${type.id}">${type.name}</option>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить"/></td>
        </tr>
    </table>
</form>
</body>
</html>