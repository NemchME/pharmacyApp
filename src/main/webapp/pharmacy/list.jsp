<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список аптек</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>Список аптек</h1>

<a href="pharmacies?action=new">Добавить аптеку</a>
<br><br>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>Название</th><th>Адрес</th><th>Телефон</th><th>Режим работы</th><th>Действия</th>
    </tr>

    <c:forEach var="pharmacy" items="${pharmacies}">
        <tr>
            <td>${pharmacy.id}</td>
            <td>${pharmacy.name}</td>
            <td>${pharmacy.address}</td>
            <td>${pharmacy.phone}</td>
            <td>${pharmacy.workingHours}</td>
            <td>${pharmacy.wayFromCenter}</td>
            <td>
                <a href="pharmacies?action=edit&id=${pharmacy.id}">Редактировать</a> |
                <a href="pharmacies?action=delete&id=${pharmacy.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
