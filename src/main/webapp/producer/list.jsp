<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список производителей</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>Список производителей</h1>

<a href="producers?action=new">Добавить производителя</a>
<br><br>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>Название</th><th>Страна</th>
    </tr>

    <c:forEach var="producer" items="${producers}">
        <tr>
            <td>${producer.id}</td>
            <td>${producer.name}</td>
            <td>${producer.country}</td>

            <td>
                <a href="producers?action=edit&id=${producer.id}">Редактировать</a> |
                <a href="producers?action=delete&id=${producer.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
