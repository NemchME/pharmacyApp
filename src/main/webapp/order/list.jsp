<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>Список заказов</title></head>
<body>
<p><a href="availabilityOfMedicines">AvailabilityOfMedicines </a></p>
<p><a href="medicines">Medicine </a></p>
<p><a href="orders">Order </a></p>
<p><a href="pharmacies">Pharmacy </a></p>
<p><a href="producers">Producer </a></p>
<p><a href="users">User<br></a></p>
<h1>Список аптек</h1>

<a href="orders?action=new">Добавить заказ</a>
<br><br>

<table border="1" cellpadding="6">
    <tr>
        <th>ID</th><th>ID пользователя</th><th>ID лекарства</th><th>ID аптеки</th><th>Количество</th><th>Статус</th><th>Время создания</th>
    </tr>

    <c:forEach var="order" items="${orders}">
        <tr>
            <td>${order.id}</td>
            <td>${order.userId}</td>
            <td>${order.medicineId}</td>
            <td>${order.pharmacyId}</td>
            <td>${order.quantity}</td>
            <td>${order.createdAt}</td>
            <td>
                <a href="orders?action=edit&id=${order.id}">Редактировать</a> |
                <a href="orders?action=delete&id=${order.id}">Удалить</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
