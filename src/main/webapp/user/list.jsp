<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список пользователей</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>Список пользователей</h1>

<a href="users?action=new">Добавить пользователя</a>
<br><br>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>Никнейм</th><th>Пароль</th><th>Эл. почта</th><th>Роль</th>
    </tr>

    <c:forEach var="user" items="${users}">
        <tr>
            <td>'${user.id}</td>
            <td>'${user.username}</td>
            <td>'${user.passwordHash}</td>
            <td>'${user.email}</td>
            <td>'${user.role}</td>
            <td>
                <a href="users?action=edit&id=${user.id}">Редактировать</a> |
                <a href="users?action=delete&id=${user.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
