<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список доступности препаратов</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>Список Доступности препаратов</h1>

<a href="availabilityOfMedicines?action=new">Добавить доступность препарата</a>
<br><br>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>ID аптеки</th><th>ID препарата</th><th>Цена</th><th>Количество</th><th>Время обновления</th>
    </tr>

    <c:forEach var="availabilityOfMedicine" items="${availabilityOfMedicines}">
        <tr>
            <td>${availabilityOfMedicine.id}</td>
            <td>${availabilityOfMedicine.pharmacyId}</td>
            <td>${availabilityOfMedicine.medicineId}</td>
            <td>${availabilityOfMedicine.price}</td>
            <td>${availabilityOfMedicine.quantity}</td>
            <td>${availabilityOfMedicine.updatedAt}</td>
            <td>
                <a href="availabilityOfMedicines?action=edit&id=${availabilityOfMedicine.id}">Редактировать</a> |
                <a href="availabilityOfMedicines?action=delete&id=${availabilityOfMedicine.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
